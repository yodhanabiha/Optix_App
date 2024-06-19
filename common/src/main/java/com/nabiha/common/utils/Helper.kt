package com.nabiha.common.utils

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException
import java.text.NumberFormat
import java.util.Locale

fun formatPrice(price: Int): String {
    val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))
    return formatter.format(price)
}

class DateTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return dateFilter(text)
    }
}

fun dateFilter(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 2 == 1 && i < 4) out += "/"
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 3) return offset +1
            if (offset <= 8) return offset +2
            return 10
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <=2) return offset
            if (offset <=5) return offset -1
            if (offset <=10) return offset -2
            return 8
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}

fun formatDateOfBirth(dateOfBirth: String?): String {
    if (dateOfBirth.isNullOrEmpty()) return ""

    // Format yang diharapkan: yyyy-MM-dd'T'HH:mm:ss
    val regexPattern = Regex("""^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}$""")
    if (regexPattern.matches(dateOfBirth)) {
        // Ambil bagian tanggal saja (YYYY-MM-DD)
        val datePart = dateOfBirth.substring(8, 10) + dateOfBirth.substring(5, 7) + dateOfBirth.substring(0, 4)
        return datePart
    }

    // Default jika tidak sesuai format yang diharapkan
    return ""
}

fun createPartFromString(value: String): RequestBody {
    return RequestBody.create("multipart/form-data".toMediaTypeOrNull(), value)
}

fun createImagePart(uri: Uri, context: Context): MultipartBody.Part {
    val contentResolver = context.contentResolver
    val type = contentResolver.getType(uri)
    val extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(type)

    // Create temporary file with correct extension
    val file = File.createTempFile("temp", ".$extension", context.cacheDir)

    // Copy input stream to temporary file
    val inputStream = contentResolver.openInputStream(uri)
    inputStream.use { input ->
        file.outputStream().use { output ->
            input?.copyTo(output)
        }
    }

    // Create request body from file
    val requestFile = file.asRequestBody(type?.toMediaTypeOrNull())

    // Create MultipartBody.Part from request file
    return MultipartBody.Part.createFormData("image", file.name, requestFile)
}


