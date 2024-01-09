package web.org.web_project_3;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

import java.util.regex.Pattern;


@FacesConverter(value = "XCoordinateValidator", managed = true)
public class XCoordinateValidator implements Converter<Double> {
    @Override
    public Double getAsObject(FacesContext facesContext, UIComponent uiComponent, String x) {
        if (x == null || x.isEmpty()) return null;
        if (!Pattern.matches("(?:-5|\\+?5)(?:[.,]0{1,15})?|(?:-[43210]|\\+?[01234])(?:[.,]\\d{1,15})?", x))
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "X: Установлено недопустимое значение X.", ""));
        return Double.parseDouble(x.replace(",", "."));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Double x) {
        return x == null ? "" : x.toString();
    }
}
