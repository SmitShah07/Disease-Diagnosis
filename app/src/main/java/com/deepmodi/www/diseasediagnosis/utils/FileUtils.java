package com.deepmodi.www.diseasediagnosis.utils;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtils {

    public FileUtils(){
    }

    //Load TF model file from the assets

    public static MappedByteBuffer loadModelfile(AssetManager manager,String modelPath) throws IOException {
        try (AssetFileDescriptor fileDescriptor = manager.openFd(modelPath);

             FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor())) {
            FileChannel fileChannel = inputStream.getChannel();
            long startOffset = fileDescriptor.getStartOffset();
            long declaredLength = fileDescriptor.getDeclaredLength();
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
        }
    }
}
