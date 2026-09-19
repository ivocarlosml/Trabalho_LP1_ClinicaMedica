class Clinica {
    double base;
    double altura;

    Clinica(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    double area() {
        return base * altura;
    }

    double perimetro() {
        return 2 * (base + altura);
    }

    boolean ehQuadrado() {
        return base == altura;
    }
}

void main() {
    var sala = new Clinica(8.0, 5.0);
    var piso = new Clinica(4.0, 4.0);

    IO.println("Sala | área: " + sala.area()
            + " | perímetro: " + sala.perimetro()
            + " | quadrado: " + sala.ehQuadrado());

    IO.println("Piso | área: " + piso.area()
            + " | perímetro: " + piso.perimetro()
            + " | quadrado: " + piso.ehQuadrado());
}