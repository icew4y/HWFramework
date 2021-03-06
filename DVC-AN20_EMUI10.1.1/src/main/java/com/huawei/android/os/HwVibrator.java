package com.huawei.android.os;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.IVibratorService;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.util.Singleton;
import com.huawei.android.os.IHwVibrator;

public class HwVibrator {
    public static final String HW_VIBRATOR_GRADE_VALUE = "haptic.grade.value";
    public static final String HW_VIBRATOR_TPYE_ALLSCREEN_UPGLIDE_MULTITASK = "haptic.allscreen.upglide_multitask";
    public static final String HW_VIBRATOR_TPYE_BATTERY_CHARGING = "haptic.battery.charging";
    public static final String HW_VIBRATOR_TPYE_CAMERA_CLICK = "haptic.camera.click";
    public static final String HW_VIBRATOR_TPYE_CAMERA_FOCUS = "haptic.camera.focus";
    public static final String HW_VIBRATOR_TPYE_CAMERA_GEAR_SLIP = "haptic.camera.gear_slip";
    public static final String HW_VIBRATOR_TPYE_CAMERA_MODE_SWITCH = "haptic.camera.mode_switch";
    public static final String HW_VIBRATOR_TPYE_CAMERA_PORTRAIT_SWITCH = "haptic.camera.portrait_switch";
    public static final String HW_VIBRATOR_TPYE_CLOCK_STOPWATCH = "haptic.clock.stopwatch";
    public static final String HW_VIBRATOR_TPYE_CLOCK_TIMER = "haptic.clock.timer";
    public static final String HW_VIBRATOR_TPYE_CONTACTS_LETTERS_INDEX = "haptic.contacts.letters_index";
    public static final String HW_VIBRATOR_TPYE_CONTACTS_LIST_SCROLL = "haptic.contacts.list_scroll";
    public static final String HW_VIBRATOR_TPYE_CONTROL_DATE_SCROLL = "haptic.control.date_scroll";
    public static final String HW_VIBRATOR_TPYE_CONTROL_EDIT_SLIP = "haptic.control.edit_slip";
    public static final String HW_VIBRATOR_TPYE_CONTROL_LETTERS_SCROLL = "haptic.control.letters_scroll";
    public static final String HW_VIBRATOR_TPYE_CONTROL_TIME_SCROLL = "haptic.control.time_scroll";
    public static final String HW_VIBRATOR_TPYE_DESKTOP_LONG_PRESS = "haptic.desktop.long_press";
    public static final String HW_VIBRATOR_TPYE_DIALLER_CLICK = "haptic.dialler.click";
    public static final String HW_VIBRATOR_TPYE_DIALLER_LONG_PRESS = "haptic.dialler.long_press";
    public static final String HW_VIBRATOR_TPYE_FINGERPRINT_ENTERING = "haptic.fingerprint.entering";
    public static final String HW_VIBRATOR_TPYE_FINGERPRINT_LIFT = "haptic.fingerprint.lift";
    public static final String HW_VIBRATOR_TPYE_FINGERPRINT_LONG_PRESS = "haptic.fingerprint.long_press";
    public static final String HW_VIBRATOR_TPYE_FINGERPRINT_UNLOCK_FAIL = "haptic.fingerprint.unlock_fail";
    public static final String HW_VIBRATOR_TPYE_GAME_SHOOTING = "haptic.game.shooting";
    public static final String HW_VIBRATOR_TPYE_HIVOICE_CLICK = "haptic.hivoice.click";
    public static final String HW_VIBRATOR_TPYE_NULL = "haptic.null";
    public static final String HW_VIBRATOR_TPYE_VIRTUALNAVIGATION_CLICK_BACK = "haptic.virtual_navigation.click_back";
    public static final String HW_VIBRATOR_TPYE_VIRTUALNAVIGATION_CLICK_HOME = "haptic.virtual_navigation.click_home";
    public static final String HW_VIBRATOR_TPYE_VIRTUALNAVIGATION_CLICK_MULTITASK = "haptic.virtual_navigation.click_multitask";
    public static final String HW_VIBRATOR_TPYE_VIRTUALNAVIGATION_LONGPRESS_HOME = "haptic.virtual_navigation.long_press";
    public static final String HW_VIBRATOR_TPYE_WALLET_TIME_SCROLL = "haptic.wallet.time_scroll";
    public static final String HW_VIBRATOR_TYPE_ALLSCREEN_SLIP_BACK = "haptic.allscreen.slip_back";
    public static final String HW_VIBRATOR_TYPE_CALCULATOR_DELETE_LONG_PRESS = "haptic.calculator.delete_long_press";
    public static final String HW_VIBRATOR_TYPE_CAMERA_CLICK_UP = "haptic.camera.click_up";
    public static final String HW_VIBRATOR_TYPE_CAMERA_LONG_PRESS = "haptic.camera.long_press";
    public static final String HW_VIBRATOR_TYPE_COMMON_CLICK = "haptic.common.click";
    public static final String HW_VIBRATOR_TYPE_COMMON_LONG_PRESS = "haptic.common.long_press";
    public static final String HW_VIBRATOR_TYPE_CONTROL_SEARCH_LONG_PRESS = "haptic.control.search_long_press";
    public static final String HW_VIBRATOR_TYPE_CONTROL_TEXT_CHOOSE_CURSOR_MOVE = "haptic.control.text_choose_cursor_move";
    public static final String HW_VIBRATOR_TYPE_CONTROL_TEXT_EDIT = "haptic.control.text_edit";
    public static final String HW_VIBRATOR_TYPE_CONTROL_WIDGET_OPERATION = "haptic.control.widget_operation";
    public static final String HW_VIBRATOR_TYPE_DESKTOP_PAGE_EDIT_CLICK = "haptic.desktop.page_edit_click";
    public static final String HW_VIBRATOR_TYPE_DESKTOP_PAGE_LONG_PRESS = "haptic.desktop.page_long_press";
    public static final String HW_VIBRATOR_TYPE_DESKTOP_WIDGET_LONG_PRESS = "haptic.desktop.widget_long_press";
    public static final String HW_VIBRATOR_TYPE_DIALLER_DELETE_LONG_PRESS = "haptic.dialler.delete_long_press";
    public static final String HW_VIBRATOR_TYPE_FINGERPRINT_IN_LONG_PRESS = "haptic.fingerprint.input_long_press";
    public static final String HW_VIBRATOR_TYPE_FLOAT_TASKS_LONGPRESS = "haptic.virtual_float_tasks.long_press";
    public static final String HW_VIBRATOR_TYPE_GALLERY_ALBUMS_LONG_PRESS = "haptic.gallery.albums_long_press";
    public static final String HW_VIBRATOR_TYPE_GALLERY_PHOTOS_LONG_PRESS = "haptic.gallery.photos_long_press";
    public static final String HW_VIBRATOR_TYPE_GALLERY_UPGLIDE_RELATED = "haptic.gallery.upglide_related";
    public static final String HW_VIBRATOR_TYPE_GRADE_STRENGTH1 = "haptic.grade.strength1";
    public static final String HW_VIBRATOR_TYPE_GRADE_STRENGTH2 = "haptic.grade.strength2";
    public static final String HW_VIBRATOR_TYPE_GRADE_STRENGTH3 = "haptic.grade.strength3";
    public static final String HW_VIBRATOR_TYPE_GRADE_STRENGTH4 = "haptic.grade.strength4";
    public static final String HW_VIBRATOR_TYPE_GRADE_STRENGTH5 = "haptic.grade.strength5";
    public static final String HW_VIBRATOR_TYPE_INPUT_VOICE_END = "haptic.input.voice_end";
    public static final String HW_VIBRATOR_TYPE_INPUT_VOICE_ENTER = "haptic.input.voice_enter";
    public static final String HW_VIBRATOR_TYPE_LOCKSCREEN_FACE_UNLOCK_FAIL = "haptic.lockscreen.face_unlock_fail";
    public static final String HW_VIBRATOR_TYPE_LOCKSCREEN_FACE_UNLOCK_RETRY = "haptic.lockscreen.face_unlock_retry";
    public static final String HW_VIBRATOR_TYPE_LOCKSCREEN_NUMBER_UNLOCK_FAIL = "haptic.lockscreen.number_unlock_fail";
    public static final String HW_VIBRATOR_TYPE_LOCKSCREEN_ONEHAND_KEYBOARD_SWITCH = "haptic.lockscreen.onehand_keyboard_switch";
    public static final String HW_VIBRATOR_TYPE_LOCKSCREEN_POWER_LONG_PRESS = "haptic.lockscreen.power_long_press";
    public static final String HW_VIBRATOR_TYPE_LOCKSCREEN_UNLOCK_CLICK = "haptic.lockscreen.unlock_click";
    public static final String HW_VIBRATOR_TYPE_LOCKSCREEN_UNLOCK_SLIP = "haptic.lockscreen.unlock_slip";
    public static final String HW_VIBRATOR_TYPE_LOCKSCREEN_UPGLIDE_SWITCHES = "haptic.lockscreen.upglide_switches";
    public static final String HW_VIBRATOR_TYPE_NOTEPAD_NOTE_LONG_PRESS = "haptic.notepad.note_long_press";
    public static final String HW_VIBRATOR_TYPE_SYSTEMUI_NOTIFICATIONS_EXPAND = "haptic.systemui.notifications_expand";
    public static final String HW_VIBRATOR_TYPE_SYSTEMUI_NOTIFICATIONS_LONG_PRESS = "haptic.systemui.notifications_long_press";
    public static final String HW_VIBRATOR_TYPE_SYSTEMUI_NOTIFICATIONS_MOVE = "haptic.systemui.notifications_move";
    public static final String HW_VIBRATOR_TYPE_SYSTEMUI_SCREEN_RECORD_STOP = "haptic.systemui.screen_record_stop";
    public static final String HW_VIBRATOR_TYPE_SYSTEMUI_SWITCHES_LONG_PRESS = "haptic.systemui.switches_long_press";
    public static final String HW_VIBRATOR_TYPE_SYSTEMUI_SWITCHES_SORT_LONG_PRESS = "haptic.systemui.switches_sort_long_press";
    public static final String HW_VIBRATOR_TYPE_VIRTUALNAVIGATION_CLICK_UP = "haptic.virtual_navigation.click_up";
    public static final String HW_VIBRATOR_TYPE_VIRTUALNAVIGATION_SINGLE_CLICK = "haptic.virtual_navigation_single.click";
    public static final String HW_VIBRATOR_TYPE_VIRTUALNAVIGATION_SINGLE_CLICK_UP = "haptic.virtual_navigation_single.click_up";
    public static final String HW_VIBRATOR_TYPE_VIRTUALNAVIGATION_SINGLE_LONGPRESS = "haptic.virtual_navigation_single.long_press";
    public static final String HW_VIBRATOR_TYPE_WEATHER_RAIN = "haptic.weather.rain";
    public static final String HW_VIBRATOR_TYPE_WEATHER_THUNDER = "haptic.weather.thunder";
    private static final Singleton<IHwVibrator> IVibratorSingleton = new Singleton<IHwVibrator>() {
        /* class com.huawei.android.os.HwVibrator.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // android.util.Singleton
        public IHwVibrator create() {
            try {
                IVibratorService vibratorService = IVibratorService.Stub.asInterface(ServiceManager.getService(Context.VIBRATOR_SERVICE));
                if (vibratorService != null) {
                    return IHwVibrator.Stub.asInterface(vibratorService.getHwInnerService());
                }
                Log.e(HwVibrator.TAG, "failed to connect VibratorService!");
                return null;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    };
    private static final String TAG = "HwVibrator";
    private static final Binder mToken = new Binder();

    public static IHwVibrator getService() {
        return IVibratorSingleton.get();
    }

    public static boolean isSupportHwVibrator(String type) {
        try {
            return getService().isSupportHwVibrator(type);
        } catch (RemoteException e) {
            Log.e(TAG, "isSupportHwVibrator fail with RemoteException");
            return false;
        }
    }

    public static void setHwVibrator(int pid, String opPkg, String type) {
        try {
            getService().setHwVibrator(pid, opPkg, mToken, type);
        } catch (RemoteException e) {
            Log.e(TAG, "setHwVibrator fail with RemoteException");
        }
    }

    public static void stopHwVibrator(int pid, String opPkg, String type) {
        try {
            getService().stopHwVibrator(pid, opPkg, mToken, type);
        } catch (RemoteException e) {
            Log.e(TAG, "stopHwVibrator fail with RemoteException");
        }
    }

    public static void setHwParameter(String command) {
        try {
            getService().setHwParameter(command);
        } catch (RemoteException e) {
            Log.e(TAG, "setHwParameter fail with RemoteException");
        }
    }

    public static String getHwParameter(String command) {
        try {
            return getService().getHwParameter(command);
        } catch (RemoteException e) {
            Log.e(TAG, "getHwParameter fail with RemoteException");
            return null;
        }
    }

    public static void notifyVibrateOptions(Bundle options) {
        try {
            getService().notifyVibrateOptions(mToken, options);
        } catch (RemoteException e) {
            Log.e(TAG, "notifyVibrateOptions fail with RemoteException");
        }
    }
}
