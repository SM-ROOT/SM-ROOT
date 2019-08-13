package com.example.appmo.Splash;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmo.R;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPassword extends Fragment {
    public static ManagerFragmentSplash states;
    View view;
    EditText txtMail;
    Button btnSend;
    String correo;
    String contraseña;
    Session session;


    public NewPassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new_password, container, false);
        sendPassword();
        showToolBarGone();
        return view;
    }

    private void showToolBarGone() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (toolbar.getVisibility() == View.GONE) {
            toolbar.setVisibility(View.VISIBLE);
        }
        showToolBar();
    }

    private void sendPassword() {
        correo = "gerardo1234silito@gmail.com";
        contraseña = "gerardo1234";
        btnSend = view.findViewById(R.id.btnSend);
        txtMail = view.findViewById(R.id.txtType);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtMail.getText().length() == 0) {
                    txtMail.setError(getString(R.string.error));
                    txtMail.getText().clear();
                } else {
                    sendMainNow();
                }

            }
        });
    }

    private void sendMainNow() {
        {


            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.googlemail.com");

            //Para conectarse al Socket del servidor
            properties.put("mail.smtp.socketFactory.port", "465");

            //Para que se envie de manera segura
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            //Para autenficar
            properties.put("mail.smtp.auth", "true");

            //Para el puerto de Gmail
            properties.put("mail.smtp.port", "465");

            try {
                //Para poder autenticar la session que se esta usando
                session = Session.getDefaultInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(correo, contraseña);
                    }
                });

                if (session != null) {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(correo));
                    message.setSubject("Este es un Subjet del correo");
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("gerardo1234silito@gmail.com"));
                    message.setContent(txtMail.getText().toString(),
                            "text/html; charset=utf-8");
                    Transport.send(message);
                    txtMail.getText().clear();
                    changeFragment(ManagerFragmentSplash.LOGIN);

                }


            } catch (Exception e) {
            }


        }
    }


    private void showToolBar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(getString(R.string.btnNewPasswordLogin));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "ic_launcher_foreground", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void changeFragment(ManagerFragmentSplash states) {
        this.states = ManagerFragmentSplash.setState(states);
        this.states.execute((MainContainerSplash) getContext());
    }

}
