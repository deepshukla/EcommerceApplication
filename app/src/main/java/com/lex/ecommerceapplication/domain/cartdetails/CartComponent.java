package com.lex.ecommerceapplication.domain.cartdetails;

import dagger.Subcomponent;


/**
 * Created by DeepS
 * <p>
 * Custom scope for cart screen, this is a child component of App Component
 * and needs to be smaller in size
 */
@CartScope
@Subcomponent(modules = {CartModule.class})
public interface CartComponent
{
    void inject(CartFragment cartFragment);

}