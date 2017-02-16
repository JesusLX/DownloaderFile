package com.limox.jesus.downloaderfile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Download_Fragment extends Fragment {

    private static final int REQUEST_WRITE_EXTERNAL = 1;
    Button btnDownload;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_download, container, false);
        btnDownload = (Button) rootView.findViewById(R.id.button);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadFile();
            }
        });
    }

    public void downloadFile() {
        if (checkWritePermission()) {
            onDownload();
        }else{
          //  Snackbar.make(getView(), R.string.cant_do_without_permissions, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_WRITE_EXTERNAL){
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                onDownload();
            }
        }
    }

    /**
     * Método que comprueba si la app tiene los permisos
     * necesarios para ejecutarse
     *
     * @return
     */
    private boolean checkWritePermission() {
        final String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), permission);

        if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
            return true;
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
            Snackbar.make(getView(), R.string.permission_write_external, Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requestPermissions(new String[]{permission}, REQUEST_WRITE_EXTERNAL);
                }
            }).show();
            return false;
        } else {
            requestPermissions(new String[]{permission}, REQUEST_WRITE_EXTERNAL);
            return false;
        }
    }

    /**
     * Method who start the download of a file
     */
    private void onDownload() {
        btnDownload.setEnabled(false);
        Intent intent = new Intent(getActivity(), Downloader.class);
        intent.setData(Uri.parse("https://cdn.pixabay.com/user/2015/01/20/20-56-42-330_250x250.jpg"));
        getActivity().startService(intent); //iniciamos el intentservice
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
