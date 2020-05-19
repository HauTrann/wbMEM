import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { LoginService } from 'app/core/login/login.service';

@Component({
  selector: 'jhi-acc-user',
  templateUrl: './acc_user.component.html',
  styleUrls: ['acc_user.scss']
})
export class AccUserComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;
  imagePath?: any;

  constructor(
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    protected router: Router,
    private _sanitizer: DomSanitizer,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.accountService.identity(true).subscribe(account => {
      this.account = account;
      if (account?.fileBase64) {
        this.imagePath = this._sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + account.fileBase64);
      }
    });
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  navigate(name: string): void {
    switch (name) {
      case 'department':
        this.router.navigate(['/department']);
        break;
      case 'user-management':
        this.router.navigate(['/admin/user-management']);
        break;
      case 'equipment-type':
        this.router.navigate(['/equipment-type']);
        break;
      case 'equipment':
        this.router.navigate(['/equipment']);
        break;
      case 'medical-supplies':
        this.router.navigate(['/medical-supplies']);
        break;
      case 'medical-supplies-type':
        this.router.navigate(['/medical-supplies-type']);
        break;
    }
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }

  logout(): void {
    this.loginService.logout();
    this.router.navigate(['']);
  }
}
