package com.cauealmeida.androidfinal.listener;

import android.view.View;

/**
 * Created by cauealmeida on 3/18/17.
 */
public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
