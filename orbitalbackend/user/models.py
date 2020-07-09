from django.db import models

class User(models.Model):
    email = models.EmailField(max_length=200)
    password = models.CharField(max_length=200)
    
    def __str__(self):
        return 'Email: ' + self.email +'\n' + 'Password: ' + self.password

# Create your models here.
