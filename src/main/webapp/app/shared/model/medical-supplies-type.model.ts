export interface IMedicalSuppliesType {
  id?: number;
  code?: string;
  name?: string;
  description?: string;
  status?: number;
}

export class MedicalSuppliesType implements IMedicalSuppliesType {
  constructor(public id?: number, public code?: string, public name?: string, public description?: string, public status?: number) {}
}
