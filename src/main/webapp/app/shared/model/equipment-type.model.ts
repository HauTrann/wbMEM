export interface IEquipmentType {
  id?: number;
  code?: string;
  name?: string;
  description?: string;
  status?: number;
}

export class EquipmentType implements IEquipmentType {
  constructor(public id?: number, public code?: string, public name?: string, public description?: string, public status?: number) {}
}
