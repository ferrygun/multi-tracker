package com.google.android.gms.samples.vision.face.multitracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.google.android.gms.samples.vision.face.multitracker.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;

import android.util.Log;

import java.util.List;

class OcrTrackerFactory implements MultiProcessor.Factory<TextBlock> {
    private GraphicOverlay mGraphicOverlay;
    private TextBlock textBlock;
    private Context mContext;

    OcrTrackerFactory(GraphicOverlay graphicOverlay,  Context context) {
        mGraphicOverlay = graphicOverlay;
        mContext = context;
    }

    @Override
    public Tracker<TextBlock> create(TextBlock textBlock) {
        OcrGraphic graphic = new OcrGraphic(mGraphicOverlay);
        return new GraphicTracker<>(mGraphicOverlay, graphic, mContext);
    }
}

class OcrGraphic extends TrackedGraphic<TextBlock> {

    private static final int TEXT_COLOR = Color.WHITE;

    private static Paint sRectPaint;
    private static Paint sTextPaint;
    private volatile TextBlock mText;

    OcrGraphic(GraphicOverlay overlay) {
        super(overlay);

        if (sRectPaint == null) {
            sRectPaint = new Paint();
            sRectPaint.setColor(TEXT_COLOR);
            sRectPaint.setStyle(Paint.Style.STROKE);
            sRectPaint.setStrokeWidth(4.0f);
        }

        if (sTextPaint == null) {
            sTextPaint = new Paint();
            sTextPaint.setColor(TEXT_COLOR);
            sTextPaint.setTextSize(54.0f);
        }
        // Redraw the overlay, as this graphic has been added.
        postInvalidate();
    }

    /**
     * Draws the text block annotations for position, size, and raw value on the supplied canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        TextBlock text = mText;
        if (text == null) {
            return;
        }

        // Draws the bounding box around the TextBlock.
        RectF rect = new RectF(text.getBoundingBox());
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);
        canvas.drawRect(rect, sRectPaint);

        // Break the text into multiple lines and draw each one according to its own bounding box.
        List<? extends Text> textComponents = text.getComponents();
        for(Text currentText : textComponents) {
            float left = translateX(currentText.getBoundingBox().left);
            float bottom = translateY(currentText.getBoundingBox().bottom);
            canvas.drawText(currentText.getValue(), left, bottom, sTextPaint);
        }
    }

    @Override
    void updateItem(TextBlock text) {
        mText = text;
        postInvalidate();
    }

 }