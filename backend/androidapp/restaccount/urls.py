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
    url(r"^get-nickname/(?P<id>[\w-]+)/$",views.GetNicknameView.as_view(),name = 'get-nickname'),
    url(r'^change-nickname/(?P<id>[\w-]+)/$',views.UpdateNicknameView.as_view(),name = 'change-nickname'),

    path('room/',views.AddRoomView.as_view(), name = 'add_room'),


    path('add-message/',views.AddMessageView.as_view(),name ='add_message'),
    url(r'^get-message/(?P<room_id>[\w-]+)/$',views.GetMessageView.as_view(),name = 'get_message'),

    path('participants/',views.AddParticipantsView.as_view(), name = 'add_participants'),
    path('list-participants/',views.ParticipantsListView.as_view(),name = 'list_participants'),
    url(r'^find-room/(?P<current_user_id>[\w-]+)/(?P<contact_id>[\w-]+)/$', views.GetRoomView.as_view(),name = 'find_room_by_users_id'),

    path('contact/',views.AddContactView.as_view(),name = 'add_contact'),
    url(r'^get-contact/(?P<current_user_id>[\w-]+)/$',views.GetContactView.as_view(),name = 'get_contact_with_current_user'),
]