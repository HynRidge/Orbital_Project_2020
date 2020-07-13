from rest_framework.serializers import (ModelSerializer,ValidationError)
from restaccount.models import RegisterUser,Message,Participants,Room
# Login

from rest_framework_simplejwt.serializers import TokenObtainPairSerializer

from rest_framework import serializers

class RegisterSerializers(ModelSerializer):
    class Meta:
        model = RegisterUser
        fields =['id',
                'first_name',
                'last_name',
                'email',
                'password',
                'phone_number',
                'nickname',
                'birthday',
                ]

    def create(self,validated_data):
        first_name = validated_data['first_name']
        last_name = validated_data['last_name']
        email = validated_data['email']
        password = validated_data['password']
        phone_number = validated_data['phone_number']
        nickname = validated_data['nickname']
        birthday = validated_data['birthday']
        user_obj = RegisterUser(
            first_name = first_name,
            last_name = last_name,
            email = email,
            phone_number = phone_number,
            nickname = nickname,
            birthday = birthday,
        )
        user_obj.set_password(password)
        user_obj.save()
        return user_obj

    def update(self, instance,validated_data):
        instance.first_name = validated_data.get('first_name',instance.first_name)
        instance.last_name = validated_data.get('last_name', instance.last_name)
        instance.email = validated_data.get('email', instance.email)
        instance.password = validated_data.get('password', instance.password)
        instance.phone_number = validated_data.get('phone_number', instance.phone_number)
        instance.nickname = validated_data.get('nickname', instance.nicknames)
        instance.birthday = validated_data.get('birthday',instance.birthday)
        instance.save()
        return instance

    def validate(self,data):
        return data
    
    def validate_phone_number(self,value):
        phone_number = value
        user_qs = RegisterUser.objects.filter(phone_number = phone_number)
        if user_qs.exists():
            raise ValidationError("This phone number is registered")
        return value


class LoginSerializer(TokenObtainPairSerializer):

    @classmethod
    def get_token(cls,user):
        token = super().get_token(user)

        token['phone_number'] = user.phone_number
        token['password'] = user.password

        return token

    def validate(self,attrs):
        data = super().validate(attrs)

        refresh = self.get_token(self.user)
        data['refresh'] = str(refresh)
        data['access'] = str(refresh.access_token)

        data['phone_number'] = self.user.phone_number

        return data


class AddRoomSerializer(ModelSerializer):
    class Meta:
        model = Room
        fields = [
            'id',
            'name',
            'type',
        ]

    def create(self,validated_data):
        name = validated_data['name']
        chat_type = validated_data['type']

        room_obj = Room(
            name = name,
            type = chat_type,
        )
        room_obj.save()
        return room_obj



class AddMessageSerializer(ModelSerializer):
    class Meta:
        model = Message
        fields = [
            'id',
            'room',
            'user',
            'message',
        ]
    def create(self,validated_data):
        message = validated_data['message']
        room = validated_data['room']
        user = validated_data['user']
        msg_obj = Message(
            message = message,
            room = room,
            user = user,
        )
        msg_obj.save()
        return msg_obj

class AddParticipantsSerializer(ModelSerializer):
    class Meta:
        model = Participants
        fields = [
            'id',
            'room',
            'user',
        ]
    def create(self,validated_data):
        user = validated_data['user']
        room = validated_data['room']
        participants_obj = Participants(
            room = room,
            user = user,
        )
        participants_obj.save()
        return participants_obj

class listRegisteredUserSerializer(ModelSerializer):
    class Meta:
        model = RegisterUser
        fields=[
            'id',
        ]