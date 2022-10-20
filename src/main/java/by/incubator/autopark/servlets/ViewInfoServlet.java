package by.incubator.autopark.servlets;

import by.incubator.autopark.facade.FacadeService;
import by.incubator.autopark.infrastructure.core.ContextService;
import by.incubator.autopark.infrastructure.core.impl.ApplicationContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/info")
public class ViewInfoServlet extends HttpServlet {
    FacadeService dtoService;

    @Override
    public void init() throws ServletException {
        super.init();
        ContextService service = new ContextService();
        ApplicationContext context = service.getContext();
        dtoService = context.getObject(FacadeService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("cars", dtoService
                .getVehicles()
                .stream()
                .filter(vehiclesDto -> id == vehiclesDto.getId()).collect(Collectors.toList()));
        req.setAttribute("rents", dtoService
                .getRents()
                .stream()
                .filter(rentsDto -> id == rentsDto.getVehicleId()).collect(Collectors.toList()));
        getServletContext().getRequestDispatcher("/jsp/viewCarInfoJSP.jsp").forward(req, resp);
    }
}
