from django.urls import path
from restaccount import views
from django.conf.urls import url
from rest_framework_simplejwt.views import TokenObtainPairView,TokenRefreshView
# from rest_framework_simplejwt import views as jwt_views

urlpatterns =[
    path('register/', views.RegisterView.as_view(), name = 'register'),

    path('api/token/login/',views.LoginView.as_view(), name='login_token'),
    path('api/token/refresh/',TokenRefreshView.as_view(),name = 'refresh_token'),

    path('registered-user/',views.RegisteredUserListView.as_view(),name = 'registered_user'),
    url(r'^register-by-phone-number/(?P<phone_number>[\w-]+)/$',views.RegisterUserByPhoneNumberView.as_view(),name='registered_by_phone_number'),

    path('room/',views.AddRoomView.as_view(), name = 'add_room'),
    path('add-message/',views.AddMessageView.as_view(),name ='add_message'),
    path('participants/',views.AddParticipantsView.as_view(), name = 'add_participants'),
    path('list-participants/',views.ParticipantsListView.as_view(),name = 'list_participants')
]