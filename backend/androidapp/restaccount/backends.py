# from django.contrib.auth.models import User
# from django.contrib.auth.backends import ModelBackend, UserModel

# from restaccount.models import RegisterUser

# class UserBackend(ModelBackend):

#     def authenticate(self,request,username=None,password=None, **kwargs):
#         try:
#             user = UserModel.objects.get(phone_number = username)
#         except UserModel.DoesNotExist:
#             return None
#         else:
#             if user.check_password(password) and self.user_can_authenticate(user):
#                 return user
#         return None

#     def get_user(self, user_id):
#         try:
#             user = UserModel.objects.get(pk=user_id)
#         except UserModel.DoesNotExist:
#             return None

#         return user if self.user_can_authenticate(user) else None

            