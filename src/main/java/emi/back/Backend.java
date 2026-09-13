package emi.back;

import emi.domain.Producto;
import emi.domain.TipoRegistro;

public class Backend {

    public static Producto[] cargarProductos(){
        Producto[] productos = {
                new Producto("4831", "Coca Cola 500ml", "", null, null, 1200.0, 48.0),
                new Producto("4619", "Coca Cola 1500ml", "", null, null, 3000.0, 48.0),
                new Producto("7240", "Coca Cola 2500ml", "", null, null, 5000.0, 48.0),

                new Producto("1275", "Yerba Rosamonte", "", null, null, 3500.0, 20.0),
                new Producto("2354", "Yerba Rosamonte", "", null, null, 3500.0, 20.0),
                new Producto("3915", "Yerba Rosamonte", "", null, null, 3500.0, 20.0),

                new Producto("3148", "Fideos Marolio", "", null, null, 890.0, 60.0),
                new Producto("5401", "Fideos Marolio", "", null, null, 890.0, 60.0),
                new Producto("1452", "Fideos Marolio", "", null, null, 890.0, 60.0),

                new Producto("8527", "Leche La Serenísima", "", null, null, 1800.0, 30.0),
                new Producto("1863", "Dilce de leche La Serenísima", "", null, null, 1800.0, 30.0),
                new Producto("8379", "Leche La Serenísima", "", null, null, 1800.0, 30.0),

                new Producto("6902", "Aceite Natura 1L", "", null, null, 4200.0, 15.0),
                new Producto("9786", "Aceite Natura 1L", "", null, null, 4200.0, 15.0),
                new Producto("6087", "Aceite Natura 1L", "", null, null, 4200.0, 15.0)

        };
        return productos;
    }

    public static String[] cargarMarcas(){
        String[] marcas = { "Koke", "Pepsi"};
        return marcas;
    }
    public static String[] cargarRubros(){
        String[] rubros = { "Almacen", "Farmacia", "Merceria", "Ferreteria", "Tecnologia"};
        return rubros;
    }
    public static String[] cargarCategorias(){
        String[] categorias = { "Gaseosa", "Vino"};
        return categorias;
    }

    public static boolean editarRegistro (TipoRegistro tipo, Object[] datos){
        return true;
    }
}
