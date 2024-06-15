package com.example.profilefeatures.edit

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Environment
import android.util.Base64
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.nabiha.designsystem.R
import com.example.profilefeatures.components.EditComponent
import com.example.profilefeatures.components.ProfileImageWithCameraIcon
import com.nabiha.apiresponse.users.UserApiUpdateRequest
import com.nabiha.common.utils.DateTransformation
import com.nabiha.common.utils.UrlApiService
import com.nabiha.common.utils.navigateToProfileScreen
import com.nabiha.designsystem.component.ScaffoldTopAppbar
import com.nabiha.entity.UserEntity
import timber.log.Timber
import java.io.File
import java.util.Date
import java.util.Locale

@Composable
internal fun EditProfileScreenRoute(
    navController: NavHostController,
    dataUser: UserEntity,
    viewModel: EditProfileViewModel = hiltViewModel()
) {
    val update by viewModel.eprofileState.collectAsStateWithLifecycle()
    EditProfileScreen(navController = navController, dataUser, viewModel)
    val context = LocalContext.current
    LaunchedEffect(update) {
        when(update){
            is EProfileState.Error -> {
                Toast.makeText(context, (update as EProfileState.Error).message, Toast.LENGTH_LONG).show()
                viewModel.resetEProfileState()
            }
            EProfileState.Idle -> {}
            EProfileState.Loading -> {}
            is EProfileState.Success -> {
                Toast.makeText(context, "Update Success!", Toast.LENGTH_LONG).show()
                navController.navigateToProfileScreen()
                viewModel.resetEProfileState()
            }
        }
    }

}

@Composable
fun EditProfileScreen(
    navController: NavHostController,
    dataUser: UserEntity,
    viewModel: EditProfileViewModel
) {
    var name by remember { mutableStateOf(dataUser.name) }
    var phone by remember { mutableStateOf(dataUser.phone) }
    var date by remember { mutableStateOf(dataUser.date_birth) }
    var gender by remember { mutableStateOf(dataUser.gender) }
    var imageUrl by remember { mutableStateOf(UrlApiService.default + dataUser.imageurl) }
    var showImagePickerDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val cameraUri = remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageUrl = it.toString()
                Timber.e("isi uri $it")
            }
        }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { isSaved ->
            Timber.e("status save $isSaved")
            if (isSaved) {
                cameraUri.value.let {
                    Timber.e("isi uri $it")
                    imageUrl = it.toString()
                }
            }
        }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            val file = createImageUri(context)

            val tmpUri = file?.let {
                FileProvider.getUriForFile(
                    context,
                    "com.nabiha.optix.provider",
                    it
                )
            }
            cameraUri.value = tmpUri
            cameraLauncher.launch(cameraUri.value)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    ScaffoldTopAppbar(
        title = "Edit Profile",
        onNavigationIconClick = navController::popBackStack,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showImagePickerDialog = true },
                        contentAlignment = Alignment.Center
                    ) {
                        ProfileImageWithCameraIcon(
                            painter = rememberAsyncImagePainter(
                                model = imageUrl
                            )
                        )
                    }
                    EditComponent(
                        label = "Name",
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    EditComponent(
                        label = "Phone",
                        value = phone,
                        onValueChange = { phone = it },
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    EditComponent(
                        label = "Date of Birth",
                        value = date,
                        onValueChange = { if (it.length <= 8) date = it },
                        modifier = Modifier.padding(top = 16.dp),
                        visualTransformation = DateTransformation(),
                    )
                    EditComponent(
                        label = "Gender",
                        value = gender,
                        onValueChange = { gender = it },
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
                item {
                    Button(
                        onClick = {
                            val dateFormat = "${date.substring(0, 2)}/${date.substring(2, 4)}/${date.substring(4)}"
                            val dataRequest = UserApiUpdateRequest(
                                name = name,
                                phone = phone,
                                gender = gender,
                                date_birth = dateFormat,
                                image = imageUrl.toUri()
                            )
                            viewModel.updateProfile(dataUser.id,dataRequest, context)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Save Change",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }

    if (showImagePickerDialog) {
        ImagePickerDialog(
            onDismissRequest = { showImagePickerDialog = false },
            onGalleryClick = {
                showImagePickerDialog = false
                galleryLauncher.launch("image/*")
            },
            onCameraClick = {
                showImagePickerDialog = false
                val permissionCheck = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                )
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    val file = createImageUri(context)
                    val tmpUri = file?.let {
                        FileProvider.getUriForFile(
                            context,
                            "com.nabiha.optix.provider",
                            it
                        )
                    }
                    cameraUri.value = tmpUri
                    cameraLauncher.launch(cameraUri.value)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        )
    }
}

@Composable
fun ImagePickerDialog(
    onDismissRequest: () -> Unit,
    onGalleryClick: () -> Unit,
    onCameraClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Choose an option",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Column {
                TextButton(
                    onClick = onGalleryClick,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.image_plus),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = "Pick from Gallery",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                TextButton(
                    onClick = onCameraClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = "Take a Photo",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    )
}

fun createImageUri(context: Context): File? {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    storageDir?.let {
        it.mkdirs()
        return File.createTempFile(
            "JPG_${timeStamp}_",
            ".jpg",
            it
        )
    }
    return null
}



