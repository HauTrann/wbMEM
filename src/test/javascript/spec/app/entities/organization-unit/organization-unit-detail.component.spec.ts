import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MedicalDeviceMngTestModule } from '../../../test.module';
import { OrganizationUnitDetailComponent } from 'app/entities/organization-unit/organization-unit-detail.component';
import { OrganizationUnit } from 'app/shared/model/organization-unit.model';

describe('Component Tests', () => {
  describe('OrganizationUnit Management Detail Component', () => {
    let comp: OrganizationUnitDetailComponent;
    let fixture: ComponentFixture<OrganizationUnitDetailComponent>;
    const route = ({ data: of({ organizationUnit: new OrganizationUnit(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalDeviceMngTestModule],
        declarations: [OrganizationUnitDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrganizationUnitDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrganizationUnitDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load organizationUnit on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.organizationUnit).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
