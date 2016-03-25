package com.shenzhoumeiwei.vcanmou.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class CustomViewGroup extends LinearLayout  {
	private ImageView imageView;
    private Button button;
    public CustomViewGroup(Context context) {
        super(context);

        this.setOrientation(VERTICAL);
        this.setWeightSum(5);

        this.imageView = new ImageView(context);
        this.button = new Button(context);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        this.imageView.setLayoutParams(layoutParams);
        this.button.setLayoutParams(layoutParams);

        this.imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.imageView.setAdjustViewBounds(true);

        this.button.setText("Goto GitHub");
        this.button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://davidschreiber.github.com/FancyCoverFlow"));
                view.getContext().startActivity(i);
            }
        });

        this.addView(this.imageView);
        this.addView(this.button);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
