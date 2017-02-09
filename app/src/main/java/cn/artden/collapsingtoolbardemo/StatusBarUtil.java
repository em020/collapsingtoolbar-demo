package cn.artden.collapsingtoolbardemo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yzsh-sym on 2017/2/8.
 */

public class StatusBarUtil {


    public static void windowRaiseUp(Window window) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        }
    }

    public static void windowRaiseToFull(Window window) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        }
    }


    public static void setStatusBarColor(Window window, int color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    public static void setNavBarColor(Window window, int color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setNavigationBarColor(color);
        }
    }


    public static void setStatusBarColor(Window window, int color, boolean isLight) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBarColor(window, color);
            setStatusBarLightOrDark(window, isLight);
        } else {
            setStatusBarColorToTranslucence(window);
        }
    }


    public static void setStatusBarLightOrDark(Window window, boolean isLight) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            String manu = Build.MANUFACTURER;

            if ("Xiaomi".equals(manu)) {

                setMIUIStatusBarLightOrDark(window, isLight);

            } else {

                int systemUiVisibility = window.getDecorView().getSystemUiVisibility();

                if (isLight) {
                    systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }

                window.getDecorView().setSystemUiVisibility(systemUiVisibility);
            }

        }

    }

    /**
     * 注意，不要和FLAG_TRANSLUCENT混淆，这里的translucent是指半透明灰色
     */
    public static void setStatusBarColorToTranslucence(Window window) {
        setStatusBarColor(window, Color.parseColor("#40000000"));
    }


    public static void setStatusBarVisible(Window window, boolean visible) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            if (visible) {
                systemUiVisibility &= ~View.SYSTEM_UI_FLAG_FULLSCREEN;
            } else {
                systemUiVisibility |= View.SYSTEM_UI_FLAG_FULLSCREEN;
            }
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        }

    }


    /*public static void setStatusBarColorToTransparent(Window window, boolean isLight) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBarColor(window, Color.TRANSPARENT);
            setStatusBarLightOrDark(window, isLight);
        } else {
            setStatusBarColorToTranslucence(window);
        }
    }*/


    private static void setMIUIStatusBarLightOrDark(Window window, boolean isLight) {

        Class clazz = window.getClass();

        try {

//            int tranceFlag = 0;
            int darkNotiIcons = 0;

            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");

            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT");

//            tranceFlag = field.getInt(layoutParams);

            field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");

            darkNotiIcons = field.getInt(layoutParams);

            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);

//            //只需要状态栏透明
//            extraFlagField.invoke(window, tranceFlag, tranceFlag);
//
//            //或  状态栏透明且黑色字体
//            extraFlagField.invoke(window, tranceFlag | darkNotiIcons, tranceFlag | darkNotiIcons);

            //清除黑色字体
            extraFlagField.invoke(window, isLight ? darkNotiIcons : 0, darkNotiIcons);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }






    /**
     * 仅对api21以上生效
     *
     * @param viewToGetWindowInsets 当api为23以上时，方法内部不会使用这个参数，而是直接通过viewPager.getRootWindowInsets；当api为21或22时，会对这个view设置一个windowInsetsListener，所以此处传入的这个view，在其他地方不能设置windowInsetsListener
     * @param viewPager
     */
    public static void postDispatchWindowInsetsToViewPagerFragments(View viewToGetWindowInsets, final View viewPager) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            viewPager.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                @SuppressLint("NewApi")
                @Override
                public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                    String foo  = insets == null ? "null" : String.valueOf(insets.getSystemWindowInsetTop());
                    Log.d("edmund", "onApplyWindowInsets of viewPager, insets: " + foo);
                    return insets;
                }
            });


            viewPager.post(new Runnable() {
                @SuppressLint("NewApi")
                @Override
                public void run() {
                    WindowInsets windowInsets = viewPager.getRootWindowInsets();
                    viewPager.dispatchApplyWindowInsets(windowInsets);
                }
            });

        } else {

            ViewCompat.setOnApplyWindowInsetsListener(viewToGetWindowInsets, new OnApplyWindowInsetsListener() {
                @Override
                public WindowInsetsCompat onApplyWindowInsets(final View v, final WindowInsetsCompat insets) {
                    Log.d("edmund", "onApplyWindowInsets of viewToGetWindowInsets, SystemWindowInsetTop = " + insets.getSystemWindowInsetTop());

                    viewPager.post(new Runnable() {
                        @Override
                        public void run() {
                            ViewCompat.dispatchApplyWindowInsets(viewPager, new WindowInsetsCompat(insets));
                        }
                    });

                    return insets;
                }
            });

            ViewCompat.setOnApplyWindowInsetsListener(viewPager, new OnApplyWindowInsetsListener() {
                @Override
                public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                    Log.d("edmund", "onApplyWindowInsets of viewPager, SystemWindowInsetTop = " + insets.getSystemWindowInsetTop());
                    return insets;
                }
            });
        }
    }
}
