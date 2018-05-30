package com.shorturl.coder;

import com.shorturl.services.data.IShortURLDataServices;

import java.net.URI;

interface IURLCoder {
    String encodeURL(URI uri, IShortURLDataServices dataServices);
}
