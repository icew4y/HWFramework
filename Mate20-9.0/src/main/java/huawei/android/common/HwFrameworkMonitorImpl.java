package huawei.android.common;

import android.common.HwFrameworkMonitor;
import android.content.Intent;
import android.os.Bundle;
import android.rms.iaware.DataContract;
import android.util.IMonitor;
import android.util.Log;
import android.util.Slog;

public class HwFrameworkMonitorImpl implements HwFrameworkMonitor {
    private static final int MAX_REASON_LEN = 512;
    private static final String TAG = "HwFrameworkMonitor";
    private static HwFrameworkMonitorImpl mInstance = null;

    private HwFrameworkMonitorImpl() {
    }

    public static synchronized HwFrameworkMonitorImpl getInstance() {
        HwFrameworkMonitorImpl hwFrameworkMonitorImpl;
        synchronized (HwFrameworkMonitorImpl.class) {
            if (mInstance == null) {
                mInstance = new HwFrameworkMonitorImpl();
            }
            hwFrameworkMonitorImpl = mInstance;
        }
        return hwFrameworkMonitorImpl;
    }

    public boolean monitor(int sceneId, Bundle params) {
        IMonitor.EventStream eventStream = IMonitor.openEventStream(sceneId);
        if (eventStream == null) {
            return false;
        }
        if (params != null) {
            switch (sceneId) {
                case 907034001:
                    int errorType = params.getInt("errorType", 1001);
                    eventStream.setParam(0, errorType);
                    Exception e = (Exception) params.getSerializable("reason");
                    if (e == null) {
                        e = new Exception();
                    }
                    String reason = Log.getStackTraceString(e).trim();
                    if (reason.length() > 512) {
                        reason = reason.substring(0, 512);
                    }
                    eventStream.setParam(1, reason);
                    Slog.i(TAG, "monitorCheckPassword: errorType=" + errorType + ", reason=" + reason);
                    break;
                case 907034002:
                    int errorType2 = params.getInt("errorType", 1001);
                    eventStream.setParam(0, errorType2);
                    StringBuilder message = new StringBuilder(params.getString("message", ""));
                    Exception exp = (Exception) params.getSerializable("reason");
                    if (exp != null) {
                        message.append(Log.getStackTraceString(exp));
                    }
                    if (message.length() > 512) {
                        message.delete(512, message.length());
                    }
                    String strMsg = message.toString();
                    eventStream.setParam(1, strMsg);
                    Slog.i(TAG, "monitorCheckPassword: errorType=" + errorType2 + ", reason=" + strMsg);
                    break;
                case 907400000:
                    eventStream.setParam(0, params.getString("package", "unknown"));
                    eventStream.setParam(1, params.getString("versionName", "unknown"));
                    eventStream.setParam(3, params.getString("extra", "unknown"));
                    break;
                case 907400002:
                    eventStream.setParam(0, params.getString("package", "unknown"));
                    eventStream.setParam(1, params.getString("versionName", "unknown"));
                    eventStream.setParam(3, params.getString(DataContract.InputProperty.ACTION, "unknown"));
                    eventStream.setParam(4, params.getInt("actionCount", 0));
                    eventStream.setParam(5, Boolean.valueOf(params.getBoolean("mmsFlag", false)));
                    eventStream.setParam(6, params.getString("receiver", "unknown"));
                    eventStream.setParam(7, params.getString("package", "unknown"));
                    break;
                case 907400003:
                    eventStream.setParam(0, params.getString("package", "unknown"));
                    eventStream.setParam(1, params.getString("versionName", "unknown"));
                    eventStream.setParam(3, params.getString(DataContract.InputProperty.ACTION, "unknown"));
                    eventStream.setParam(4, params.getFloat("receiveTime", 0.0f));
                    eventStream.setParam(5, params.getString("receiver", "unknown"));
                    Object objIntent = params.getParcelable(DataContract.BaseAttr.INTENT);
                    if (objIntent != null) {
                        eventStream.setParam(6, ((Intent) objIntent).toString());
                        break;
                    }
                    break;
                case 907400016:
                    eventStream.setParam(0, params.getString("cpuState", "unknown"));
                    eventStream.setParam(1, params.getString("cpuTime", "unknown"));
                    eventStream.setParam(2, params.getString("extra", "unknown"));
                    break;
                case 907400018:
                    eventStream.setParam(0, params.getString("component", "unknown"));
                    eventStream.setParam(1, params.getString("reason", "unknown"));
                    break;
                case 942030001:
                    eventStream.setParam("ProcName", params.getString("proc_name", "unknown"));
                    eventStream.setParam("StatDur", params.getInt("stat_duration", 0));
                    eventStream.setParam("CyclRefMaxCnt", params.getInt("circref_rcycl_cnt", 0));
                    eventStream.setParam("CyclRefMaxDur", params.getInt("circref_rcycl_max_duration", 0));
                    eventStream.setParam("MemLeakAvrg", params.getInt("mem_leak_avrg", 0));
                    eventStream.setParam("MemLeakPeak", params.getInt("mem_leak_peak", 0));
                    eventStream.setParam("MemAllocSpac", params.getFloat("mem_alloc_space_util", 0.0f));
                    eventStream.setParam("MemAllocAbnmCnt", params.getInt("mem_alloc_abnormal", 0));
                    eventStream.setParam("RcAbnmCnt", params.getInt("rc_abnormal", 0));
                    eventStream.setParam("RefGlobalWaterline", params.getInt("global_water_line", 0));
                    eventStream.setParam("RefWeakWaterline", params.getInt("weak_water_line", 0));
                    eventStream.setParam("RefThreadslocalWaterline", params.getString("threads_local_water_line", "unknown"));
                    eventStream.setParam("RefNativeTableSize", params.getInt("native_table_size", 0));
                    eventStream.setParam("MemConsumMplFiles", params.getInt("consum_mpl_files", 0));
                    eventStream.setParam("MemConsumClassLocator", params.getInt("consum_class_locator", 0));
                    eventStream.setParam("MemReflectManageHeap", params.getInt("reflect_manage_heap", 0));
                    eventStream.setParam("MemGcManageHeap", params.getInt("gc_manage_heap", 0));
                    eventStream.setParam("MemCyclePattern", params.getString("cycle_pattern", "unknown"));
                    break;
            }
        }
        boolean result = IMonitor.sendEvent(eventStream);
        Slog.i(TAG, "Monitor for " + sceneId + ", result=" + result);
        IMonitor.closeEventStream(eventStream);
        return result;
    }
}
