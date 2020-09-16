package burullus.digitom.app.utils;

/**
 * Created by Ezequiel Adrian on 24/02/2017.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.google.android.gms.common.images.Size;

public class Utils {

    public static int dpToPx(final int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenHeight(final Context c) {
        final WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        final Display display = wm.getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public static int getScreenWidth(final Context c) {
        final WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        final Display display = wm.getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static float getScreenRatio(final Context c) {
        final DisplayMetrics metrics = c.getResources().getDisplayMetrics();
        return ((float) metrics.heightPixels / (float) metrics.widthPixels);
    }

    public static int getScreenRotation(final Context c) {
        final WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getRotation();
    }

    public static int distancePointsF(final PointF p1, final PointF p2) {
        return (int) Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    public static PointF middlePoint(final PointF p1, final PointF p2) {
        if (p1 == null || p2 == null)
            return null;
        return new PointF((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    public static Size[] sizeToSize(final android.util.Size[] sizes) {
        final Size[] size = new Size[sizes.length];
        for (int i = 0; i < sizes.length; i++) {
            size[i] = new Size(sizes[i].getWidth(), sizes[i].getHeight());
        }
        return size;
    }
}
