package com.example.miami.fragments.Registration;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.miami.R;
import com.example.miami.models.registration.RegistrationState;
import com.example.miami.viewModels.RegistrationViewModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotoFragment extends Fragment {
    private RegistrationViewModel mRegistrationViewModel;
//    private MediatorLiveData<RegistrationState> mRegistrationState = new RegistrationState<>();

    private static final int RESULT_LOAD_IMAGE = 1;
    private ImageView imageView;
    private String mPath;

    public PhotoFragment() {
        super();
        mPath = "";
    }

    public static PhotoFragment newInstance() {
        return new PhotoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        imageView = view.findViewById(R.id.add_photo);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, RESULT_LOAD_IMAGE);
                    } else {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRegistrationViewModel = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);

        Button buttonReg = view.findViewById(R.id.photo_button);

        mRegistrationViewModel.getProgress()
                .observe(getViewLifecycleOwner(), new PhotoObserver(buttonReg));

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPath.equals("")) {
                    Toast.makeText(getContext(), "ХАХАХА пас ноль", Toast.LENGTH_LONG).show();
                    return;
                }

                mRegistrationViewModel.uploadAvatar(mPath);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = requireActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            mPath = picturePath;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case RESULT_LOAD_IMAGE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                } else {
                    Toast.makeText(getContext(), "Fuck you!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public class PhotoObserver implements Observer<RegistrationState> {
        private final Button mButton;

        public PhotoObserver(Button button) {
            mButton = button;
        }

        @Override
        public void onChanged(RegistrationState registrationState) {
            if (registrationState == RegistrationState.FAILED) {
                Toast.makeText(getContext(), "Неожиданные проблемы", Toast.LENGTH_LONG).show();
                mButton.setEnabled(true);
            } else if (registrationState == RegistrationState.ERROR) {
                Toast.makeText(getContext(), "ХАХА АШИБКА", Toast.LENGTH_LONG).show();
                mButton.setEnabled(true);
            } else if (registrationState == RegistrationState.IN_PROGRESS) {
                Toast.makeText(getContext(), "ХАХА ПРАГРЕС", Toast.LENGTH_LONG).show();
                mButton.setEnabled(false);
            } else if (registrationState == RegistrationState.AVATAR_SUCCESS) {
                // рисовать
                Toast.makeText(getContext(), "ХАХА АВТАР СУКЕСС", Toast.LENGTH_LONG).show();
            } else if (registrationState == RegistrationState.SUCCESS) {
                Toast.makeText(getContext(), "ХАХА СУКЕСС", Toast.LENGTH_SHORT).show();
            }
        }
    }
}