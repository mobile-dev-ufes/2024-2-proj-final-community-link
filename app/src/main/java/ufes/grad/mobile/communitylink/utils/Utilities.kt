package ufes.grad.mobile.communitylink.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.io.InputStream

class Utilities {
    companion object {
        fun notify(context: Context?, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun imageToBytes(context: Context, uri: Uri): ByteArray {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val bitmap = inputStream?.let { BitmapFactory.decodeStream(it) }
            if (bitmap == null) throw Exception("Algo deu errado...")

            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            return byteArrayOutputStream.toByteArray()
        }
    }
}
