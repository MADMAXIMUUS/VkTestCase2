package ru.madmax.vktestcase2

import android.Manifest.permission.MANAGE_EXTERNAL_STORAGE
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import ru.madmax.vktestcase2.databinding.ActivityMainBinding
import ru.madmax.vktestcase2.presentation.FilesListFragment
import ru.madmax.vktestcase2.util.requestPermissions
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requestPermissions(MANAGE_EXTERNAL_STORAGE)
            if (!Environment.isExternalStorageManager()) {
                MaterialAlertDialogBuilder(this)
                    .setTitle(getString(R.string.dialog_title))
                    .setMessage(getString(R.string.dialog_message))
                    .setPositiveButton(getString(R.string.give_access)) { dialog, _ ->
                        val intent = Intent().apply {
                            action = Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
                            data = Uri.fromParts("package", packageName, null)
                        }
                        dialog.dismiss()
                        startActivity(intent)
                    }
                    .setNegativeButton(getString(R.string.dont_give_access)) { dialog, _ ->
                        dialog.dismiss()
                        exitProcess(0)
                    }
                    .create()
                    .show()
            }
        } else {
            requestPermissions(READ_EXTERNAL_STORAGE)
        }

        val fragment = FilesListFragment()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        fragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.navHostFragment, fragment)
        }
        transaction.commit()

    }

}