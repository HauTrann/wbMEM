export interface IOrganizationUnit {
  id?: number;
  code?: string;
  name?: string;
  description?: string;
  status?: number;
}

export class OrganizationUnit implements IOrganizationUnit {
  constructor(public id?: number, public code?: string, public name?: string, public description?: string, public status?: number) {}
}
