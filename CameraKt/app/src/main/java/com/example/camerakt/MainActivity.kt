package com.example.camerakt

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.security.cert.CertPath
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1 // 카메라 사진 촬영 요청 코드(신호값)
    lateinit var curPhotoPath: String // 문자열 형태의 사진 경로 값(초기 값을 null로 시작하고 싶을 때)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPermission() // 권한을 체크하는 메소드 수행

        btn_camera.setOnClickListener {
            takeCapture() // 기본 카메라 앱을 실행하여 사진 촬영
        }
    }

    /**
     * 카메라 촬영
     */
    private fun takeCapture() {
        // 기본카메라 앱 실행(Intent)
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePicktureIntent -> // 기본 카메라 실행
            takePicktureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.example.camerakt.fileprovider", // 안전한 파일
                        it
                    )
                    takePicktureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(
                        takePicktureIntent,
                        REQUEST_IMAGE_CAPTURE
                    ) // 사진을 찍고 나서난 뒤의 결과값 받는다(for result)

                }
            }
        }
    }

    /**
     * 이미지 파일 생성
     */
    private fun createImageFile(): File {
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date()) // 사진을 구분하기 위함
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", storageDir)
            .apply { curPhotoPath = absolutePath }
    }

    /**
     * 테드 퍼미션 설정
     */
    private fun setPermission() {
        val permission = object : PermissionListener {
            override fun onPermissionGranted() { // 설정해놓은 위험 권한들이 허용 되었을 경우 이곳을 수행함
                Toast.makeText(this@MainActivity, "권한이 허용 되었습니다", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) { // 설정해놓은 위험 권한들 중 거부를 한 경우 이곳을 수행
                Toast.makeText(this@MainActivity, "권한이 거부 되었습니다", Toast.LENGTH_SHORT).show()
            }
        }

        TedPermission.with(this)
            .setPermissionListener(permission)
            .setRationaleMessage("카메라를 사용하시려면 권한을 허용해주세요")
            .setDeniedMessage("권한을 거부하셨습니다 [앱 설정] -> [권한] 항목에서 허용해주세요")
            .setPermissions(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
            )
            .check()
    }

    // startActivityForResult를 통해서 기본 카메라 앱으로부터 받아온 사진 결과 값
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 이미지를 성공적으로 가져왔다면,
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val bitmap: Bitmap
            val file = File(curPhotoPath) // 절대경로 들어있음
            if (Build.VERSION.SDK_INT < 28) { // 안드로이드 9.0 (pie) 보다 낮은 경우
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Uri.fromFile(file))
                iv_profile.setImageBitmap(bitmap)
            } else { // 안드로이드 9.0 (pie) 보다 높은 경우
                val decode = ImageDecoder.createSource( // 변환 필요
                    this.contentResolver,
                    Uri.fromFile(file)
                )
                bitmap = ImageDecoder.decodeBitmap(decode)
                iv_profile.setImageBitmap(bitmap)
            }
            savePhoto(bitmap)
        }
    }

    /**
     * 갤러리에 저장
     */
    private fun savePhoto(bitmap: Bitmap) {
        val folderPath =
            Environment.getExternalStorageDirectory().absolutePath + "/Pictures/" // 사진 폴더로 저장하기 위한 경로
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date()) // 사진을 구분하기 위함
        val fileName = "${timestamp}.jpeg"
        val folder = File(folderPath)
        if (!folder.isDirectory) { // 현재 해당 경로에 폴더가 존재하는지 검사
            // 해당 경로에 폴더 존재 하지 않는 경우
            folder.mkdirs() // make directory 해당경로에 폴더 자동 생성
        }

        // 실제적 저장처리
        val out = FileOutputStream(folderPath + fileName)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        Toast.makeText(this, "사진이 앨범에 저장되었습니다.", Toast.LENGTH_SHORT).show()
    }
}