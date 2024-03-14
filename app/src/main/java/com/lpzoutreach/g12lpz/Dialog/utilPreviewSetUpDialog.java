package com.lpzoutreach.g12lpz.Dialog;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.lpzoutreach.g12lpz.Models.PreviewSetUpModel;
import com.lpzoutreach.g12lpz.R;
import com.squareup.picasso.Picasso;

public class utilPreviewSetUpDialog extends AppCompatDialogFragment {

    PreviewSetUpModel data;
    Context context;
    private View view;

    public utilPreviewSetUpDialog(Context context, PreviewSetUpModel data){
        this.context = context;
        this.data = data;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_preview_set_up_mode,null);

        TextView tv_none = (TextView) view.findViewById(R.id.tv_church_none_set_up);
        ConstraintLayout lc_data = (ConstraintLayout) view.findViewById(R.id.cl_church_data_set_up);
        ImageView iv_church = (ImageView) view.findViewById(R.id.iv_church_set_up);
        TextView tv_church_name = (TextView) view.findViewById(R.id.tv_church_name_set_up);
        TextView tv_church_address = (TextView) view.findViewById(R.id.tv_church_address_set_up);

        TextView tv_network_none = (TextView) view.findViewById(R.id.tv_network_leader_none_set_up);
        ConstraintLayout lc_network_data = (ConstraintLayout) view.findViewById(R.id.cl_network_leader_data_set_up);
        ImageView iv_network_leader = (ImageView) view.findViewById(R.id.iv_network_leader_set_up);
        TextView tv_name_network_leader = (TextView) view.findViewById(R.id.tv_name_network_leader_set_up);
        TextView tv_church_network_leader = (TextView) view.findViewById(R.id.tv_church_network_leader_set_up);
        TextView tv_network_name = (TextView) view.findViewById(R.id.tv_network_name_set_up);

        TextView tv_invite_none = (TextView) view.findViewById(R.id.tv_invite_none_set_up);
        ConstraintLayout lc_invite_data = (ConstraintLayout) view.findViewById(R.id.cl_invite_data_set_up);
        ImageView iv_invite = (ImageView) view.findViewById(R.id.iv_invite_set_up);
        TextView tv_name_invite = (TextView) view.findViewById(R.id.tv_name_invite_set_up);
        TextView tv_church_invite = (TextView) view.findViewById(R.id.tv_church_invite_set_up);
        TextView tv_network_invite = (TextView) view.findViewById(R.id.tv_invite_set_up);


        if(data.getChurchID()>0){
            lc_data.setVisibility(View.VISIBLE);
            tv_none.setVisibility(View.GONE);

            if(data.getChurch_photo().isEmpty()){
                iv_church.setImageResource(R.drawable.default_photo);
            }else{
                Picasso.get().load(data.getChurch_photo())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(iv_church);
            }

            tv_church_name.setText(data.getChurch_name());
            tv_church_address.setText(data.getChurch_address());

        }else{
            lc_data.setVisibility(View.GONE);
            tv_none.setVisibility(View.VISIBLE);

            if(data.getChurch_photo().isEmpty()){
                iv_church.setImageResource(R.drawable.default_photo);
            }else{
                Picasso.get().load(data.getChurch_photo())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(iv_church);
            }

            tv_church_name.setText(data.getChurch_name());
            tv_church_address.setText(data.getChurch_address());

        }

        if(data.getUserID_network_leader()>0){
            lc_network_data.setVisibility(View.VISIBLE);
            tv_network_none.setVisibility(View.GONE);

            if(data.getPhoto_network_leader().isEmpty()){
                iv_network_leader.setImageResource(R.drawable.default_photo);
            }else{
                Picasso.get().load(data.getPhoto_network_leader())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(iv_network_leader);
            }

            tv_name_network_leader.setText(data.getName_network_leader());
            tv_church_network_leader .setText(data.getChurch_network_leader());

            if(!data.getChurch_network_name_leader().equals("null")){
                tv_network_name.setText(data.getChurch_network_name_leader());
            }else{
                tv_network_name.setVisibility(View.GONE);
            }


        }else{
            lc_network_data.setVisibility(View.GONE);
            tv_network_none.setVisibility(View.VISIBLE);
        }

        if(data.getUserID_invite()>0){
            lc_invite_data.setVisibility(View.VISIBLE);
            tv_invite_none.setVisibility(View.GONE);

            if(data.getPhoto_invite().isEmpty()){
                iv_invite.setImageResource(R.drawable.default_photo);
            }else{
                Picasso.get().load(data.getPhoto_invite())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(iv_invite);
            }

            tv_name_invite.setText(data.getName_invite());

            if(!data.getChurch_invite().equals("null")){
                tv_church_invite.setText(data.getChurch_invite());
            }else{
                tv_church_invite.setVisibility(View.GONE);
            }

            if(!data.getNetwork_name_invite().equals("null")){
                tv_network_invite.setText(data.getNetwork_name_invite());
            }else{
                tv_network_invite.setVisibility(View.GONE);
            }

        }else{
            lc_invite_data.setVisibility(View.GONE);
            tv_invite_none.setVisibility(View.VISIBLE);
        }



        builder.setView(view)
                .setCancelable(false)
                .setPositiveButton(context.getResources().getString(R.string.yes_button_dialog), (dialog, which) -> data.getEventListener().myCallBack("modal-trigger-yes",""))
                .setNegativeButton(context.getResources().getString(R.string.no_button_dialog), (dialog, which) -> data.getEventListener().myCallBack("modal-trigger-no",""));
        return builder.create();
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);
    }
}
