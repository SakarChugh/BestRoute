package bestroute.dto;

import java.util.List;

public class MinimumTimePathInfo {
    List<Node> minimumTimePath;
    Double minimumTime;

    public MinimumTimePathInfo(List<Node> minimumTimePath, Double minimumTime) {
        this.minimumTimePath = minimumTimePath;
        this.minimumTime = minimumTime;
    }

    public List<Node> getMinimumTimePath() {
        return minimumTimePath;
    }

    public void setMinimumTimePath(List<Node> minimumTimePath) {
        this.minimumTimePath = minimumTimePath;
    }

    public Double getMinimumTime() {
        return minimumTime;
    }

    public void setMinimumTime(Double minimumTime) {
        this.minimumTime = minimumTime;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder("Path taken for minimum time: ");
        for (int i = 0; i < minimumTimePath.size(); i++) {
            returnString.append(minimumTimePath.get(i).getName()).append(i != minimumTimePath.size() - 1 ? "->" : "");
        }
        returnString.append("\nMinimum time for given combination: ").append(minimumTime);
        return returnString.toString();
    }
}
