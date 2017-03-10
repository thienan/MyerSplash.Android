package com.juniperphoton.myersplash.widget;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.juniperphoton.myersplash.App;
import com.juniperphoton.myersplash.R;
import com.juniperphoton.myersplash.utils.ColorUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownloadCompleteView extends FrameLayout {

    @BindView(R.id.widget_set_as_rl)
    RelativeLayout setAsBtn;

    @BindView(R.id.widget_set_as_root_rl)
    RelativeLayout setAsRL;

    @BindView(R.id.set_as_tv)
    TextView setAsTextView;

    private String mFileUrl;

    public DownloadCompleteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.widget_download_complete_view, this);
        ButterKnife.bind(this);
    }

    public void setFilePath(String filePath) {
        mFileUrl = filePath;
    }

    @OnClick(R.id.widget_set_as_rl)
    void setAs() {
        if (mFileUrl != null) {
            File file = new File(mFileUrl);
            Uri uri = FileProvider.getUriForFile(App.Companion.getInstance(), App.Companion.getInstance().getString(R.string.authorities), file);
            Intent intent = WallpaperManager.getInstance(App.Companion.getInstance()).getCropAndSetWallpaperIntent(uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            App.Companion.getInstance().startActivity(intent);
        }
    }

    public void setThemeBackColor(int color) {
        setAsRL.setBackground(new ColorDrawable(color));
        setAsTextView.setTextColor(ColorUtil.Companion.isColorLight(color) ? Color.BLACK : Color.WHITE);
    }
}
