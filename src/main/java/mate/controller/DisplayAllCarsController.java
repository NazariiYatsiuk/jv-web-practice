package mate.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.lib.Injector;
import mate.model.Car;
import mate.service.CarService;

@WebServlet(urlPatterns = "/cars")
public class DisplayAllCarsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private final CarService carService
            = (CarService) injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Car> allCars = carService.getAll();
        req.setAttribute("cars", allCars);
        req.getRequestDispatcher("/WEB-INF/views/car/all.jsp").forward(req, resp);
    }

    public static String driversListFromCar(Car car) {
        String collect = car.getDrivers()
                .stream()
                .map(d -> d.getName())
                .collect(Collectors.joining());
        return collect;
    }
}