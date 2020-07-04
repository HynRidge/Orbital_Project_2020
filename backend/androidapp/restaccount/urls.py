from django.urls import path
from restaccount import views
# from rest_framework_simplejwt import views as jwt_views

urlpatterns =[
    path('register/', views.RegisterView.as_view(), name = 'register'),
    path('api/token/login/',views.LoginView.as_view(), name='login_token')
]