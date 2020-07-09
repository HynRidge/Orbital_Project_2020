from django.db import models   
from django.contrib.auth.models import AbstractBaseUser,BaseUserManager
from django.contrib.auth.models import PermissionsMixin
# from django.utils.translation import ugettext_lazy as _
from phone_field import PhoneField
from django.contrib.postgres.fields import ArrayField




class RegisterUserManager(BaseUserManager):
    def create_user(self, phone_number, password=None):
        """
        Creates and saves a User with the given email, date of
        birth and password.
        """
        if not phone_number:
            raise ValueError('Users must have an phone number')

        user = self.model(
            phone_number=phone_number,
        )

        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, phone_number, password=None):
        """
        Creates and saves a superuser with the given email, date of
        birth and password.
        """
        user = self.create_user(
            phone_number,
            password=password,
            
        )
        user.is_admin = True
        user.is_staff = True
        user.is_active = True
        user.save(using=self._db)
        return user


class RegisterUser(AbstractBaseUser, PermissionsMixin):                                  
    """                                                                            
    A custom class for user authentication. It gets rid of 
    'username' and uses 'email' for that purpose.                                                      
    """                                             
    first_name = models.CharField(max_length=255,name ='first_name')
    last_name = models.CharField(max_length=255, name = 'last_name') 
    email = models.EmailField(max_length=255, name = 'email')
    birthday = models.DateField(name = 'birthday', null = True)
    nickname = models.CharField(max_length=255,name = 'nickname')
    phone_number = models.CharField(max_length=12,unique=True, name = 'phone_number')

    # private_chats = models.ForeignKey(PrivateChat,on_delete =models.CASCADE,)
    # group_chats = models.ForeignKey('GroupChat', on_delete = models.CASCADE)
    # message = models.ForeignKey('Message', on_delete = models.CASCADE)

    is_active = models.BooleanField(default=True,)          
    is_staff = models.BooleanField(default=False)                                  
    is_admin = models.BooleanField(default=False)                               

    objects = RegisterUserManager()                                                      

    USERNAME_FIELD = 'phone_number'                                                       
    REQUIRED_FIELDS = []                                                           

    def _str_(self):                                                             
        return self.phone_number                                                          


class Room(models.Model):
    # user_ID =ArrayField(models.IntegerField(blank=True,null=True),size = 2,name='users',null=True)
    chat_type = models.IntegerField(name = 'type')
    # name = models.CharField(max_length = 255,name ='private_chat', default='private_chat')
    name = models.CharField(max_length=255,name = 'name')

    def __str__(self):
        return self.name

class Participants(models.Model):
    user = models.ForeignKey(RegisterUser,on_delete=models.CASCADE)
    room = models.ForeignKey(Room,on_delete=models.CASCADE,)

class Message(models.Model):
    room = models.ForeignKey(Room, on_delete=models.CASCADE)
    user = models.ForeignKey(RegisterUser,on_delete=models.CASCADE)
    message = models.TextField(name = 'message')