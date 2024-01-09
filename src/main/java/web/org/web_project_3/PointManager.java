package web.org.web_project_3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named @SessionScoped
public class PointManager implements Serializable {
    @Inject
    private DataRepository repository;
    @Getter
    private final Dot dot = new Dot();
    @Getter
    private final List<Dot> dots = new ArrayList<>();
    @Getter
    private final List<Double> availableR = List.of(1.0, 2.0, 3.0, 4.0, 5.0);
    @Getter @Setter
    private List<Double> selectedR = new ArrayList<>();


    public void shoot() {
        for (Double r : selectedR) {
            dot.setR(r);
            saveShot(dot);
        }
    }

    public void shootPlot() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String error = requestParameters.get("error");
        if (error != null) {
            MessageManager.error(error);
            return;
        }
        Dot dot = new Dot();
        try {
            dot.setX(Double.parseDouble(requestParameters.get("x")));
            dot.setY(Double.parseDouble(requestParameters.get("y")));
            dot.setR(Double.parseDouble(requestParameters.get("r")));
        } catch (NumberFormatException | NullPointerException e) {
            MessageManager.error("Данные введены неверно.");
            return;
        }
        saveShot(dot);
    }

    private void saveShot(Dot dot) {
        Dot newDot = repository.create(dot);
        dots.add(newDot);
    }
}
