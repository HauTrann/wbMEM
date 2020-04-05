import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDepartmentEmployee, DepartmentEmployee } from 'app/shared/model/department-employee.model';
import { DepartmentEmployeeService } from './department-employee.service';

@Component({
  selector: 'jhi-department-employee-update',
  templateUrl: './department-employee-update.component.html'
})
export class DepartmentEmployeeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    employeeID: [],
    departmentID: [],
    status: []
  });

  constructor(
    protected departmentEmployeeService: DepartmentEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ departmentEmployee }) => {
      this.updateForm(departmentEmployee);
    });
  }

  updateForm(departmentEmployee: IDepartmentEmployee): void {
    this.editForm.patchValue({
      id: departmentEmployee.id,
      employeeID: departmentEmployee.employeeID,
      departmentID: departmentEmployee.departmentID,
      status: departmentEmployee.status
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const departmentEmployee = this.createFromForm();
    if (departmentEmployee.id !== undefined) {
      this.subscribeToSaveResponse(this.departmentEmployeeService.update(departmentEmployee));
    } else {
      this.subscribeToSaveResponse(this.departmentEmployeeService.create(departmentEmployee));
    }
  }

  private createFromForm(): IDepartmentEmployee {
    return {
      ...new DepartmentEmployee(),
      id: this.editForm.get(['id'])!.value,
      employeeID: this.editForm.get(['employeeID'])!.value,
      departmentID: this.editForm.get(['departmentID'])!.value,
      status: this.editForm.get(['status'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepartmentEmployee>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
