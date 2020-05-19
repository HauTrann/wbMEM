import { Route } from '@angular/router';

import { NotificationComponent } from './notification.component';

export const NOTIFICATION_ROUTE: Route = {
  path: 'notification',
  component: NotificationComponent,
  data: {
    authorities: [],
    pageTitle: 'home.title'
  }
};
