Pour tester les fonctionalités du projet voila les api cree :

Produit :

- creation du produit :
    API : /api/products (methode post)
     Payload attendu :

     {
       
        "code": "Produit1",
        "name": "myProduct",
        "description": "the description full text3",
        "image": "",
        "category": null,
        "price": 999,
        "quantity": null,
        "internalReference": null,
        "shellId": null,
        "inventoryStatus": null,
        "rating": null,
        "createdAt": null,
        "updatedAt": null
    }

- Aficher tous les produits :
   Api: /api/products (methode get)

-chercher un produit : 
   Api : /api/products/{id} (methode get)

-Modifier un produit :
   Api : /api/products/{id} (methode put)
    Payload attendu :

     {
       
        "code": "Produit updated ....",
        "name": "myProduct",
        "description": "the description full text3",
        "image": "",
        "category": null,
        "price": 999,
        "quantity": null,
        "internalReference": null,
        "shellId": null,
        "inventoryStatus": null,
        "rating": null,
        "createdAt": null,
        "updatedAt": null
    }

-Supprimer un produit :
   Api : /api/products/{id}     (methode delete)


- Creation d'un user : 

api :  /api/account (methode post)

 Payload attendu :
{
       
        "username": "admin",
        "firstName": "admin",
        "email": "admin@admin.com",
        "password": "admin"
         
    }

-Connecter un user :
api :  /api/token

 Payload attendu :

 {
       
        "username": "admin",
        "password": "admin"
         
    }



- Pour l'affichage des produits ajouter par un clients sur le panier  : 
Api :  /api/cart-products

{
       
        "count": 12,
        "user": {
       
        "username": "admin",
        "password": "admin"
         
    },
        "product": {
        "id": 1,
        "code": null,
        "name": "product1",
        "description": "description1",
        "image": null,
        "category": null,
        "price": 1,
        "quantity": null,
        "internalReference": null,
        "shellId": null,
        "inventoryStatus": null,
        "rating": null,
        "createdAt": null,
        "updatedAt": null
    }
    }

   nb : la meme chose par rapport favory-products:

   Api :  /api/favory-products

et pour la restriction demondé par rapport l'ajoute et les autres operation du crud du product pour le user qui a un mail admin@admin.com il est implementer sur le code .
