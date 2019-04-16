package com.example.qr_reader_example;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Base64OutputStream;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

import java.util.Map;

import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
  private static final String CHANNEL = "com.rq-exam/decode";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);

    new MethodChannel(getFlutterView(), CHANNEL).setMethodCallHandler(new MethodChannel.MethodCallHandler() {
      @TargetApi(Build.VERSION_CODES.FROYO)
      @Override
      public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

        final Map<String, Object> arguments = methodCall.arguments();

        if (methodCall.method.equals("decodeQr")) {
          System.out.println("INSIDE DECODE QR ---------------------------------------------------------");
          String message = arguments.get("data").toString();
          Charset iso88591charset = Charset.forName("ISO-8859-1");
          try {
            ByteBuffer b = ByteBuffer.wrap(message.getBytes());
            CharBuffer outputBuffer = iso88591charset.decode(b);

            CharsetDecoder encoder=iso88591charset.newDecoder();
            CharBuffer a = encoder.decode(b);
            System.out.println(Base64.encodeToString(message.getBytes("ISO-8859-1"),Base64.DEFAULT));
            System.out.println(Arrays.toString(outputBuffer.array()));
            System.out.println(Arrays.toString(message.getBytes("UTF-8")));
            System.out.println(Arrays.toString(message.getBytes("ISO-8859-1")));
            System.out.println(Arrays.toString(message.getBytes("US-ASCII")));
            System.out.println(Arrays.toString(message.getBytes("UTF-16BE")));
            System.out.println(Arrays.toString(message.getBytes("UTF-16LE")));
            System.out.println(Arrays.toString(message.getBytes("UTF-16")));
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          } catch (CharacterCodingException e) {
            e.printStackTrace();
          }
        }

      }
    });
  }
}
