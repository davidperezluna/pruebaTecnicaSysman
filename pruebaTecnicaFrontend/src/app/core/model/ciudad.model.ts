export class Ciudad {
    nombre: string;
    codigo: string;
    id?: number;
  
    constructor(
        nombre: string,
        codigo: string,
        id?: number,
    ) {
      this.nombre = nombre;
      this.codigo = codigo;
      if (id) {
        this.id = id;
      }
    }
  }   