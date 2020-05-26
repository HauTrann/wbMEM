import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrganizationUnit } from 'app/shared/model/organization-unit.model';

@Component({
  selector: 'jhi-organization-unit-detail',
  templateUrl: './organization-unit-detail.component.html'
})
export class OrganizationUnitDetailComponent implements OnInit {
  organizationUnit: IOrganizationUnit | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ organizationUnit }) => (this.organizationUnit = organizationUnit));
  }

  previousState(): void {
    window.history.back();
  }
}
