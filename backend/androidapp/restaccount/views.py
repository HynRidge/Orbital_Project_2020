from django.shortcuts import render
from django.http import HttpResponse,JsonResponse   
from rest_framework.parsers import JSONParser
from restaccount.models import RegisterUser
# Login
from restaccount.serializers import RegisterSerializers,LoginSerializer
# LoginSerializers
from django.views.decorators.csrf import csrf_exempt
from rest_framework.generics import CreateAPIView

from rest_framework_simplejwt.views import TokenObtainPairView

from rest_framework.permissions import (AllowAny,IsAuthenticated)
# from rest_framework.generics import CreateAPIView

class RegisterView(CreateAPIView):
    permission_classes = (AllowAny,)
    serializer_class = RegisterSerializers
    queryset = RegisterUser.objects.all()

class LoginView(TokenObtainPairView):
    serializer_class = LoginSerializer