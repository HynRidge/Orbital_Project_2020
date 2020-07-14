from django.shortcuts import render
from django.http import HttpResponse,JsonResponse   
from rest_framework.parsers import JSONParser
from restaccount.models import RegisterUser,Message,Room,Participants
# Login
from restaccount.serializers import RegisterSerializers,LoginSerializer,AddMessageSerializer,AddRoomSerializer
from restaccount.serializers import AddParticipantsSerializer,listRegisteredUserSerializer,GetMessageSerializer
# LoginSerializers
from django.views.decorators.csrf import csrf_exempt
from rest_framework.generics import CreateAPIView,ListAPIView

from rest_framework_simplejwt.views import TokenObtainPairView

from rest_framework.permissions import (AllowAny,IsAuthenticated)
# from rest_framework.generics import CreateAPIView

class RegisterView(CreateAPIView):
    permission_classes = (AllowAny,)
    serializer_class = RegisterSerializers
    queryset = RegisterUser.objects.all()

class LoginView(TokenObtainPairView):
    permission_classes = IsAuthenticated
    serializer_class = LoginSerializer


class AddRoomView(CreateAPIView):
    serializer_class = AddRoomSerializer
    queryset = Room.objects.all()

class AddMessageView(CreateAPIView):
    serializer_class = AddMessageSerializer
    queryset = Message.objects.all()

class AddParticipantsView(CreateAPIView):
    serializer_class  = AddParticipantsSerializer
    queryset =Participants.objects.all()
    
class ParticipantsListView(ListAPIView):
    serializer_class=AddParticipantsSerializer
    queryset = Participants.objects.all()

class RegisteredUserListView(ListAPIView):
    serializer_class=listRegisteredUserSerializer
    queryset = RegisterUser.objects.all()

class RegisterUserByPhoneNumberView(ListAPIView):
    serializer_class=listRegisteredUserSerializer
    

    def get_queryset(self):
        phone_number = self.kwargs['phone_number']

        return RegisterUser.objects.filter(phone_number = phone_number)

class GetMessageView(ListAPIView):
    serializer_class =  GetMessageSerializer
    

    def get_queryset(self):
        room_id = self.kwargs['room_id']
        user_id = self.kwargs['user_id']

        return Message.objects.filter(room = room_id, user = user_id)