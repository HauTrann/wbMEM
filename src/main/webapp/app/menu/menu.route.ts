import { Route } from '@angular/router';

import { MenuComponent } from './menu.component';

export const MENU_ROUTE: Route = {
  path: 'menu',
  component: MenuComponent,
  data: {
    authorities: [],
    pageTitle: 'home.title'
  }
};
