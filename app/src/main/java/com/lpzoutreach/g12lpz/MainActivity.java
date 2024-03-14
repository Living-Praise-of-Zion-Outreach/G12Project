package com.lpzoutreach.g12lpz;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.lpzoutreach.g12lpz.Activity.WebsiteBrowser;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressDialogBar;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.lpzoutreach.g12lpz.Utility.system.accessVariable;
import com.lpzoutreach.g12lpz.Utility.themes.themes;
import com.lpzoutreach.g12lpz.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    //private FirebaseAuth auth;
    private ProgressDialogBar progressDialogBar;
    private elUtil eventListenerUtil = elUtil.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize(){

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialogBar = new ProgressDialogBar(MainActivity.this);

        click_event();
        validation_clear();
        googleSignIn();

        binding.tvEmailLogIn.setText(sharedData.get_email(getApplicationContext()));
        binding.tvPasswordLogIn.setText(sharedData.get_password(getApplicationContext()));

    }

    private void click_event(){


        binding.tvPrivacyPolicyMain.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, WebsiteBrowser.class);
            intent.putExtra("title",  "Privacy Policy");
            intent.putExtra("url",accessUrl.BASE_URL +  "privacy-policy");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right_activity,R.anim.slide_out_left_activity);
        });
        binding.tvDataPrivacyMain.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, WebsiteBrowser.class);
            intent.putExtra("title",  "Data Policy");
            intent.putExtra("url",accessUrl.BASE_URL +  "data-privacy");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right_activity,R.anim.slide_out_left_activity);
        });
        binding.tvTermsMain.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, WebsiteBrowser.class);
            intent.putExtra("title", "Terms and Conditions");
            intent.putExtra("url",accessUrl.BASE_URL +  "terms-and-conditions");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right_activity,R.anim.slide_out_left_activity);
        });



    }

    private void validation_clear(){
        binding.tvEmailLogIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                themes.outlineColor(getApplicationContext(),binding.tilEmailLogIn,themes.NORMAL_COLOR_MODE);
            }
        });

        binding.tvPasswordLogIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                themes.outlineColor(getApplicationContext(),binding.tilPasswordLogIn,themes.NORMAL_COLOR_MODE);
            }
        });
    }


    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;

    private void googleSignIn(){
        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build())
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        //.setServerClientId("736299307090-4ps6i6h1fqqdnsvku6u4ahqmpjd5o1n5.apps.googleusercontent.com")
                                .setServerClientId("736299307090-i0ohcuj1thosmkffih6i0e06j5lit2jf.apps.googleusercontent.com")
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                // Automatically sign in when exactly one credential is retrieved.
                .setAutoSelectEnabled(true)
                .build();


        ActivityResultLauncher<IntentSenderRequest> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                     if(result.getResultCode()==Activity.RESULT_OK){
                         try{
                             SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                             String idToken = credential.getGoogleIdToken();

                             if(idToken!=null){
                                 Toast.makeText(getApplicationContext(),credential.getId(),Toast.LENGTH_LONG).show();
                             }

                         }catch (ApiException e){
                             Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                             e.printStackTrace();
                         }
                     }
                    }
                });



        /*
        binding.ivGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"ha",Toast.LENGTH_LONG).show();

                oneTapClient.beginSignIn(signInRequest)
                        .addOnSuccessListener(MainActivity.this, result -> {

                            IntentSenderRequest intentSenderRequest =
                                    new IntentSenderRequest.Builder(result.getPendingIntent().getIntentSender()).build();
                            activityResultLauncher.launch(intentSenderRequest);


                        })
                        .addOnFailureListener(MainActivity.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // No saved credentials found. Launch the One Tap sign-up flow, or
                                // do nothing and continue presenting the signed-out UI.
                                //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                                Log.d("TAG", e.getLocalizedMessage());
                            }
                        });


            }
        });

         */


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }


    private boolean validateEmail(String data){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return !data.matches(emailPattern);
    }

    public void logInFirebase()
    {

        String username = binding.tvEmailLogIn.getText().toString().trim();
        String password = binding.tvPasswordLogIn.getText().toString().trim();

        if(username.isEmpty())
        {
            binding.tvEmailLogIn.requestFocus();
            binding.tilEmailLogIn.setHelperText("* Username is required.");
            binding.tilEmailLogIn.setBoxStrokeColor(getResources().getColor(R.color.error_color));
            progressDialogBar.hide();
        }
        else if(password.isEmpty())
        {
            binding.tvPasswordLogIn.requestFocus();
            binding.tilPasswordLogIn.setHelperText(accessVariable.PASSWORD_REQUIRED);
            binding.tilPasswordLogIn.setBoxStrokeColor(getResources().getColor(R.color.error_color));
            progressDialogBar.hide();
        }
        else
        {

            /*
            auth.signInWithEmailAndPassword(username,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {

                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        Map<String, Object> user = new HashMap<>();
                        user.put("primary_photo","");
                        user.put(accessVariable.G12_PROFILE_INFORMATION_COL_FIRST_NAME,"");
                        user.put(accessVariable.G12_PROFILE_INFORMATION_COL_MIDDLE_NAME,"");
                        user.put(accessVariable.G12_PROFILE_INFORMATION_COL_LAST_NAME,"");
                        user.put(accessVariable.G12_PROFILE_INFORMATION_COL_NAME_EXT,"");
                        user.put(accessVariable.G12_PROFILE_INFORMATION_COL_DATE_OF_BIRTH,"");
                        user.put(accessVariable.G12_PROFILE_INFORMATION_COL_PLACE_OF_BIRTH,"");
                        user.put(accessVariable.G12_PROFILE_INFORMATION_COL_SEX,"");
                        user.put(accessVariable.G12_PROFILE_INFORMATION_COL_CIVIL_STATUS,"");
                        user.put(accessVariable.G12_PROFILE_INFORMATION_COL_HEIGHT,"");
                        user.put(accessVariable.G12_PROFILE_INFORMATION_COL_HEIGHT_METRIC,"");
                        user.put(accessVariable.G12_PROFILE_INFORMATION_COL_WEIGHT,"");
                        user.put(accessVariable.G12_PROFILE_INFORMATION_COL_WEIGHT_METRIC,"");
                        user.put(accessVariable.G12_PROFILE_INFORMATION_COL_BLOOD_TYPE,"");
                        user.put("email_address",binding.tvEmailLogIn.getText().toString());

                        db.collection("users").document(auth.getUid()).collection("Personal Information").document(auth.getUid()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                progressDialogBar.hide();
                                //sharedData.set_userID(getApplicationContext(),auth.getUid().toString());
                                Intent intent = new Intent(getApplication(), Home.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right_activity,R.anim.slide_out_left_activity);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                    else
                    {
                        utilToast.showWarning(MainActivity.this,"Email Address and Password does not match");
                    }
                }
            }).addOnFailureListener(MainActivity.this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    String[] error = e.toString().split(":");
                    if(error.length>1){
                        utilToast.showWarning(MainActivity.this,error[1]);
                        progressDialogBar.hide();
                    }
                }
            });

             */

        }

    }

}