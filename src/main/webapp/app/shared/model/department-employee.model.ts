export interface IDepartmentEmployee {
  id?: number;
  employeeID?: number;
  departmentID?: number;
  status?: number;
}

export class DepartmentEmployee implements IDepartmentEmployee {
  constructor(public id?: number, public employeeID?: number, public departmentID?: number, public status?: number) {}
}
