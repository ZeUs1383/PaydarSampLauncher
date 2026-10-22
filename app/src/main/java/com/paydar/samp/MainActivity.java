package com.paydar.samp;

import android.app.Activity;
import android.os.Bundle;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

public class MainActivity extends Activity {
    int bg = Color.rgb(5,11,24), panel = Color.rgb(12,26,47), purple = Color.rgb(118,87,246), muted = Color.rgb(166,185,218);
    EditText ip, port;
    TextView status;
    GradientDrawable shape(int color, int radius) { GradientDrawable d = new GradientDrawable(); d.setColor(color); d.setCornerRadius(radius); d.setStroke(1, Color.rgb(35,55,88)); return d; }
    TextView text(String s, int size, int color, boolean bold) { TextView t = new TextView(this); t.setText(s); t.setTextSize(size); t.setTextColor(color); if(bold)t.setTypeface(null, Typeface.BOLD); t.setGravity(Gravity.CENTER); t.setPadding(8,8,8,8); return t; }
    LinearLayout.LayoutParams lp(int w,int h) { return new LinearLayout.LayoutParams(w,h); }
    void gap(LinearLayout root,int h){ View v=new View(this); root.addView(v,lp(1,h)); }
    Button button(String label, int color) { Button b = new Button(this); b.setText(label); b.setTextColor(Color.WHITE); b.setAllCaps(false); b.setTextSize(15); b.setBackground(shape(color,24)); return b; }
    @Override public void onCreate(Bundle b) { super.onCreate(b); getWindow().setStatusBarColor(bg); getWindow().setNavigationBarColor(bg);
        ScrollView scroll = new ScrollView(this); scroll.setBackgroundColor(bg);
        LinearLayout root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(22,26,22,22); scroll.addView(root);
        TextView logo = text("♛",58, purple,true); root.addView(logo,lp(-1,76));
        TextView title = text("PAYDAR SAMP",30,Color.WHITE,true); root.addView(title);
        TextView sub = text("SA-MP SERVER LAUNCHER",12,muted,true); root.addView(sub); gap(root,26);
        LinearLayout card = new LinearLayout(this); card.setOrientation(LinearLayout.VERTICAL); card.setPadding(16,18,16,18); card.setBackground(shape(panel,24)); root.addView(card,lp(-1,-2));
        card.addView(text("سرور پایدار سمپ",22,Color.WHITE,true));
        card.addView(text("RolePlay • Economy • Fun",13,muted,false)); gap(card,14);
        TextView iplabel=text("آدرس IP سرور",13,muted,true); card.addView(iplabel,lp(-1,-2));
        ip=new EditText(this); ip.setSingleLine(true); ip.setText("10.29.2.204"); ip.setHint("مثلاً 192.168.1.10"); ip.setTextColor(Color.WHITE); ip.setHintTextColor(muted); ip.setSelectAllOnFocus(true); ip.setPadding(14,8,14,8); ip.setBackground(shape(Color.rgb(7,17,33),16)); card.addView(ip,lp(-1,52)); gap(card,10);
        card.addView(text("پورت سرور",13,muted,true),lp(-1,-2));
        port=new EditText(this); port.setSingleLine(true); port.setInputType(2); port.setText("7777"); port.setTextColor(Color.WHITE); port.setPadding(14,8,14,8); port.setBackground(shape(Color.rgb(7,17,33),16)); card.addView(port,lp(-1,52)); gap(root,16);
        Button connect=button("اتصال به سرور  ➜",purple); root.addView(connect,lp(-1,56));
        connect.setOnClickListener(v->{ String address=ip.getText().toString().trim(); String p=port.getText().toString().trim(); if(address.isEmpty()||p.isEmpty()){Toast.makeText(this,"IP و پورت را وارد کن",Toast.LENGTH_SHORT).show();return;} String endpoint=address+":"+p; ((ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("Paydar Samp server",endpoint)); status.setText("آدرس "+endpoint+" کپی شد. برای ورود، کلاینت سازگار SA-MP باید روی گوشی نصب باشد."); try { Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("samp://"+endpoint)); startActivity(i); } catch(Exception e){ Toast.makeText(this,"کلاینتی برای باز کردن لینک SA-MP پیدا نشد؛ آدرس کپی شد.",Toast.LENGTH_LONG).show(); } });
        gap(root,12); Button copy=button("کپی IP و پورت",panel); root.addView(copy,lp(-1,50)); copy.setOnClickListener(v->{String s=ip.getText().toString().trim()+":"+port.getText().toString().trim(); ((ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("server",s)); Toast.makeText(this,"کپی شد: "+s,Toast.LENGTH_SHORT).show();});
        gap(root,18); status=text("نکته: برای اتصال از گوشی، گوشی و کامپیوتر باید روی یک شبکه باشند و فایروال ویندوز اجازه اتصال UDP به پورت سرور را بدهد.",13,muted,false); status.setBackground(shape(panel,18)); root.addView(status,lp(-1,-2)); gap(root,18);
        root.addView(text("PAYDAR SAMP  •  نسخه 1.0.0",12,muted,false)); setContentView(scroll);
    }
}
