export interface IMedicalSupplies {
  id?: number;
  code?: string;
  name?: string;
  medicalSuppliesTypeID?: number;
  description?: string;
  status?: number;
}

export class MedicalSupplies implements IMedicalSupplies {
  constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public medicalSuppliesTypeID?: number,
    public description?: string,
    public status?: number
  ) {}
}
