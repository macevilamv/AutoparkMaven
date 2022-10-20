package by.incubator.autopark.servlets;

import by.incubator.autopark.collections.VehicleCollection;
import by.incubator.autopark.facade.FacadeService;
import by.incubator.autopark.infrastructure.core.ContextService;
import by.incubator.autopark.infrastructure.core.ObjectFactory;
import by.incubator.autopark.infrastructure.core.impl.ApplicationContext;
import by.incubator.autopark.service.Workroom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/viewDiagnostic")
public class ViewDiagnosticServlet extends HttpServlet {
    FacadeService dtoService;
    Workroom workroom;
    VehicleCollection entityCollection;

    @Override
    public void init() throws ServletException {
        super.init();
        ContextService service = new ContextService();
        ApplicationContext context = service.getContext();
        dtoService = context.getObject(FacadeService.class);
        workroom = context.getObject(Workroom.class);
        entityCollection = context.getObject(VehicleCollection.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("brokenCars", workroom.getRepairedVehiclesId((entityCollection.getVehicles())));
        req.setAttribute("cars", dtoService.getVehicles());
        getServletContext().getRequestDispatcher("/jsp/viewDiagnosticJSP.jsp").forward(req, resp);
    }
}