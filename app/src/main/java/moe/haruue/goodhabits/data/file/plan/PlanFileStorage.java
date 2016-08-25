package moe.haruue.goodhabits.data.file.plan;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import moe.haruue.goodhabits.App;
import moe.haruue.goodhabits.data.file.plan.exception.NoSuchPlanException;
import moe.haruue.goodhabits.data.file.plan.exception.PlanIdConflictException;
import moe.haruue.goodhabits.model.Plan;
import moe.haruue.goodhabits.util.FileUtils;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public enum  PlanFileStorage {

    INSTANCE;

    public static PlanFileStorage getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private File getStorageDir() {
        String path = App.getContext().getFilesDir().getAbsolutePath() + "/plans";
        File dir = new File(path);
        if (dir.exists() && !dir.isDirectory()) {
            dir.delete();
        }
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    private File getFileFor(Plan plan) {
        String path = getStorageDir().getAbsolutePath() + "/" + plan.planId + ".json";
        return new File(path);
    }

    private File getFileFor(String planId) {
        String path = getStorageDir().getAbsolutePath() + "/" + planId + ".json";
        return new File(path);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void storagePlan(Plan plan, boolean isOverride) {
        File file = getFileFor(plan);
        if (file.exists() && file.isDirectory()) {
            file.delete();
        }
        if (file.exists() && !isOverride) {
            throw new PlanIdConflictException(plan.planId);
        }
        String json = plan.toJson();
        FileUtils.writeStringToFile(json, file);
    }

    public Plan getPlan(String planId) {
        File file = getFileFor(planId);
        return getPlanFrom(file);
    }

    private String planIdOf(File file) {
        String fileName = file.getName();
        return fileName.substring(0, fileName.length() - ".json".length());
    }

    public Plan getPlanFrom(File file) {
        if (!file.exists() || (file.exists() && file.isDirectory())) {
            throw new NoSuchPlanException(planIdOf(file));
        }
        String json = FileUtils.readStringFromFile(file);
        return new Gson().fromJson(json, Plan.getPlanTypeFromJson(json));
    }

    public List<Plan> getAllPlan() {
        File dir = getStorageDir();
        List<Plan> planList = new ArrayList<>(0);
        for (File f: dir.listFiles((file, s) -> s.contains(".json"))) {
            planList.add(getPlanFrom(f));
        }
        return planList;
    }

}
