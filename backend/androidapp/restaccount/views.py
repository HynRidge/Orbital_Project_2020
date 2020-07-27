from django.shortcuts import render
from django.http import HttpResponse,JsonResponse   
from rest_framework.parsers import JSONParser
from restaccount.models import RegisterUser,Message,Room,Participants,Contact
# Login
from restaccount.serializers import RegisterSerializers,LoginSerializer,AddMessageSerializer,AddRoomSerializer
from restaccount.serializers import AddParticipantsSerializer,listRegisteredUserSerializer,GetMessageSerializer
from restaccount.serializers import AddContactSerializer,GetContactWithCurrentUser,GetNickname,GetRoom,ChangeNickname
# LoginSerializers
from django.views.decorators.csrf import csrf_exempt
from rest_framework.generics import CreateAPIView,ListAPIView,RetrieveAPIView,UpdateAPIView

from rest_framework_simplejwt.views import TokenObtainPairView

from rest_framework.permissions import (AllowAny,IsAuthenticated)
# from rest_framework.generics import CreateAPIView

class RegisterView(CreateAPIView):
    permission_classes = (AllowAny,)
    serializer_class = RegisterSerializers
    queryset = RegisterUser.objects.all()

class LoginView(TokenObtainPairView):
    # permission_classes = IsAuthenticated
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
        # user_id = self.kwargs['user_id']
        # receiver_id = self.kwargs['receiver_id']

        return Message.objects.filter(room = room_id)


class AddContactView(CreateAPIView):
    serializer_class = AddContactSerializer
    queryset = Contact.objects.all()

class GetContactView(ListAPIView):
    serializer_class = GetContactWithCurrentUser

    def get_queryset(self):
        current_user_id = self.kwargs['current_user_id']

        return Contact.objects.filter(current_user_id = current_user_id) 

class GetNicknameView(RetrieveAPIView):
    serializer_class = GetNickname
    lookup_field = 'id'
    
    def get_queryset(self):
        user_id = self.kwargs['id']
        return RegisterUser.objects.filter(pk = user_id)

class GetRoomView(ListAPIView):
    serializer_class = GetRoom

    def get_queryset(self):
        current_user_id = self.kwargs['current_user_id']
        contact_id = self.kwargs['contact_id']

        return Participants.objects.filter(user = current_user_id) and Participants.objects.filter(user = contact_id)

class UpdateNicknameView(UpdateAPIView):
    serializer_class = ChangeNickname
    queryset = RegisterUser.objects.all()
    lookup_field = 'id'

    # def update(self,request):
    #     instance = self.get_object()
    #     instance.nickname = request.data.get("nickname")
    #     instance.save()
    #     return instance
