import { Injectable } from '@angular/core';
import { AppNotification } from '../models/notification.model';
import { MOCK_NOTIFICATIONS } from '../data/mock-notifications';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private notifications: AppNotification[] = [...MOCK_NOTIFICATIONS];

  getNotificationsForUser(userId: number): AppNotification[] {
    return this.notifications
      .filter(notification => notification.userId === userId)
      .sort((a, b) => b.createdAt.localeCompare(a.createdAt));
  }

  pushNotification(notification: AppNotification): void {
    this.notifications.unshift(notification);
  }

  createNotification(userId: number, title: string, message: string): void {
    const newNotification: AppNotification = {
      id: Date.now(),
      userId,
      title,
      message,
      read: false,
      createdAt: new Date().toISOString()
    };

    this.notifications.unshift(newNotification);
  }

  markAsRead(notificationId: number): void {
    const notification = this.notifications.find(n => n.id === notificationId);

    if (notification) {
      notification.read = true;
    }
  }

  getUnreadCount(userId: number): number {
    return this.notifications.filter(
      notification => notification.userId === userId && !notification.read
    ).length;
  }
}
